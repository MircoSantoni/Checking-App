package com.cuentacorrienteapp.cuentacorrienteapp.services.implementation;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.*;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cuentacorrienteapp.cuentacorrienteapp.dtos.cuenta.*;
import com.cuentacorrienteapp.cuentacorrienteapp.entities.Comprobante;
import com.cuentacorrienteapp.cuentacorrienteapp.entities.Cuenta;
import com.cuentacorrienteapp.cuentacorrienteapp.entities.Movimiento;
import com.cuentacorrienteapp.cuentacorrienteapp.enums.EstadoCuenta;
import com.cuentacorrienteapp.cuentacorrienteapp.exceptions.ResourceNotFoundException;
import com.cuentacorrienteapp.cuentacorrienteapp.mappers.CuentaMapper;
import com.cuentacorrienteapp.cuentacorrienteapp.repositories.CuentaRepository;
import com.cuentacorrienteapp.cuentacorrienteapp.repositories.MovimientoRepository;
import com.cuentacorrienteapp.cuentacorrienteapp.services.CuentaService;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CuentaServiceImpl implements CuentaService {

    private final MovimientoRepository movimientoRepository;
    private final CuentaRepository cuentaRepository;
    private final CuentaMapper cuentaMapper;

    @Override
    @Transactional(readOnly = true)
    public Set<ResponseCuentaDto> findAll() {
        checkAccountStatus();
        try {
            return cuentaRepository.findAll().stream()
                    .<ResponseCuentaDto>map(cuenta -> ResponseCuentaDto.builder()
                            .id(cuenta.getId())
                            .saldo(cuenta.getSaldo())
                            .name(cuenta.getName())
                            .nombreProveedor(cuenta.getNombreProveedor())
                            .numeroCelular(cuenta.getNumeroCelular())
                            .emailProveedor(cuenta.getEmailProveedor())
                            .estadoCuenta(cuenta.getEstadoCuenta())
                            .direccionProveedor(cuenta.getDireccionProveedor())
                            .fechaBajaLogicaCuenta(cuenta.getFechaBajaLogicaCuenta())
                            .movimiento(cuenta.getMovimientos() != null ? cuenta.getMovimientos() : null)
                            .build())
                    .collect(Collectors.toSet());
        } catch (Exception ex) {
            throw new RuntimeException("Error al buscar las cuentas", ex);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseCuentaDto findById(String id) {
        checkAccountStatus();
        try {
            return cuentaRepository.findById(id)
                    .map(cuenta -> ResponseCuentaDto.builder()
                            .id(cuenta.getId())
                            .saldo(cuenta.getSaldo())
                            .name(cuenta.getName())
                            .nombreProveedor(cuenta.getNombreProveedor())
                            .numeroCelular(cuenta.getNumeroCelular())
                            .emailProveedor(cuenta.getEmailProveedor())
                            .estadoCuenta(cuenta.getEstadoCuenta())
                            .direccionProveedor(cuenta.getDireccionProveedor())
                            .fechaBajaLogicaCuenta(cuenta.getFechaBajaLogicaCuenta())
                            .movimiento(cuenta.getMovimientos() != null ? cuenta.getMovimientos() : null)
                            .build())
                    .orElseThrow(() -> new RuntimeException("Cuenta no encontrada con ID: " + id));
        } catch (Exception ex) {
            throw new RuntimeException("Error al buscar la cuenta con ID: " + id, ex);
        }
    }

    @Override
    @Transactional
    public ResponseCuentaDto save(RequestCuentaDto requestCuentaDto) {
        if (requestCuentaDto == null) {
            throw new IllegalArgumentException("El request no puede ser null");
        }

        Cuenta nuevaCuenta = cuentaMapper.requestCuentaDtoToCuenta(requestCuentaDto);
        nuevaCuenta.setIsValid(true);
        nuevaCuenta.setEstadoCuenta(EstadoCuenta.CANCELADA);
        cuentaRepository.save(nuevaCuenta);

        return cuentaMapper.cuentaToResponseCuentaDto(nuevaCuenta);
    }

    @Override
    @Transactional
    public ResponseUpdateIsValidDto updateIsValid(String id) {
        Cuenta cuenta = cuentaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Esta cuenta no existe"));

        cuenta.setIsValid(!cuenta.isValid());

        if (!cuenta.isValid()) {
            ZoneId zoneId = ZoneId.of("America/Argentina/Buenos_Aires");
            cuenta.setFechaBajaLogicaCuenta(ZonedDateTime.now(zoneId).toLocalDateTime());
        } else {
            cuenta.setFechaBajaLogicaCuenta(null);
        }

        cuentaRepository.save(cuenta);

        return cuentaMapper.cuentaToResponseUpdateIsValidDto(cuenta);
    }

    @Transactional
    public ResponseAddMovimientoDto addMovimientoToCuenta(RequestAddMovimientoDto requestAddMovimientoDto) {
        Cuenta cuenta = cuentaRepository.findById(requestAddMovimientoDto.cuentaId())
                .orElseThrow(() -> new RuntimeException("Cuenta no encontrada"));

        if (!cuenta.isValid()) {
            throw new IllegalStateException("No se pueden añadir movimientos a una cuenta inactiva");
        }

        Movimiento movimiento = movimientoRepository.findById(requestAddMovimientoDto.movimientoId())
                .orElseThrow(() -> new RuntimeException("Movimiento no encontrado"));

        if (movimiento.getCuenta() != null) {
            throw new IllegalStateException("El movimiento ya está asociado a una cuenta");
        }

        movimiento.setIsValid(true);

        cuenta.setSaldo(cuenta.getSaldo() + movimiento.getImporteMovimiento());

        cuenta.addMovimiento(movimiento);

        Cuenta cuentaActualizada = cuentaRepository.save(cuenta);

        return cuentaMapper.cuentaToResponseAddMovimientoDto(cuentaActualizada);
    }

    @Override
    @Transactional
    public void checkAccountStatus() {
        List<Cuenta> accounts = cuentaRepository.findAll();

        for (Cuenta cuenta : accounts) {
            boolean isPending = false;

            // Check if the account has any movements
            if (cuenta.getMovimientos().isEmpty()) {
                cuenta.setEstadoCuenta(EstadoCuenta.CANCELADA);
                cuenta.setIsValid(true);
                cuentaRepository.save(cuenta);
                continue;
            }

            // Iterate through movements
            for (Movimiento movimiento : cuenta.getMovimientos()) {
                // Skip if movement has no comprobantes
                if (movimiento.getComprobantes().isEmpty()) {
                    isPending = true;
                    break;
                }

                // Calculate total amount of comprobantes
                long totalComprobantes = movimiento.getComprobantes().stream()
                        .filter(Comprobante::isValid)
                        .mapToLong(comprobante -> comprobante.getMontoComprobante().longValue())
                        .sum();

                // Check if total comprobantes match movement amount
                if (totalComprobantes != movimiento.getImporteMovimiento()) {
                    isPending = true;
                    break;
                }
            }

            if (isPending) {
                cuenta.setEstadoCuenta(EstadoCuenta.PENDIENTE);
            } else {
                cuenta.setEstadoCuenta(EstadoCuenta.CANCELADA);
                cuenta.setIsValid(true);
            }

            cuentaRepository.save(cuenta);
        }
    }

}
