    package com.cuentacorrienteapp.cuentacorrienteapp.services.implementation;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.*;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cuentacorrienteapp.cuentacorrienteapp.dtos.cuenta.*;
import com.cuentacorrienteapp.cuentacorrienteapp.entities.Cuenta;
import com.cuentacorrienteapp.cuentacorrienteapp.entities.Movimiento;
import com.cuentacorrienteapp.cuentacorrienteapp.exceptions.ResourceNotFoundException;
import com.cuentacorrienteapp.cuentacorrienteapp.mappers.CuentaMapper;
import com.cuentacorrienteapp.cuentacorrienteapp.repositories.CuentaRepository;
import com.cuentacorrienteapp.cuentacorrienteapp.repositories.MovimientoRepository;
import com.cuentacorrienteapp.cuentacorrienteapp.services.CuentaService;

import io.micrometer.core.ipc.http.HttpSender.Response;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CuentaServiceImpl implements CuentaService{

    private final MovimientoRepository movimientoRepository;
    private final CuentaRepository cuentaRepository;
    private final CuentaMapper cuentaMapper;

    @Override
    @Transactional( readOnly = true)
    public Set<ResponseCuentaDto> findAll() {
        try {
            return cuentaRepository.findAll().stream()
                .<ResponseCuentaDto>map(cuenta -> ResponseCuentaDto.builder()
                    .id(cuenta.getId())
                    .saldo(cuenta.getSaldo())
                    .name(cuenta.getName())
                    .nombreProveedor(cuenta.getNombreProveedor())
                    .numeroCelular(cuenta.getNumeroCelular())
                    .emailProveedor(cuenta.getEmailProveedor())
                    .direccionProveedor(cuenta.getDireccionProveedor())
                    .fechaBajaLogicaCuenta(cuenta.getFechaBajaLogicaCuenta())
                    .movimiento(cuenta.getMovimientos() != null ? cuenta.getMovimientos() :null)
                    .build())
                .collect(Collectors.toSet());
        } catch (Exception ex) {
            throw new RuntimeException("Error al buscar las cuentas", ex);
        }
    }

    @Override
    @Transactional( readOnly = true)
    public ResponseCuentaDto findById(String id) {
        Cuenta cuenta = cuentaRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Esta cuenta no existe"));
        return cuentaMapper.cuentaToResponseCuentaDto(cuenta);
    }

    @Override
    @Transactional 
    public ResponseCuentaDto save(RequestCuentaDto requestCuentaDto) {
        if (requestCuentaDto == null) {
            throw new IllegalArgumentException("El request no puede ser null");
        }

        Cuenta nuevaCuenta = cuentaMapper.requestCuentaDtoToCuenta(requestCuentaDto);
        nuevaCuenta.setIsValid(true);
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
        // Buscar la cuenta
        Cuenta cuenta = cuentaRepository.findById(requestAddMovimientoDto.cuentaId())
            .orElseThrow(() -> new RuntimeException("Cuenta no encontrada"));
    
        // Verificar si la cuenta es válida
        if (!cuenta.isValid()) {
            throw new IllegalStateException("No se pueden añadir movimientos a una cuenta inactiva");
        }
    
        // Buscar el movimiento existente
        Movimiento movimiento = movimientoRepository.findById(requestAddMovimientoDto.movimientoId())
            .orElseThrow(() -> new RuntimeException("Movimiento no encontrado"));
    
        // Verificar que el movimiento no esté ya asociado a otra cuenta
        if (movimiento.getCuenta() != null) {
            throw new IllegalStateException("El movimiento ya está asociado a una cuenta");
        }
    
        // Establecer el estado del movimiento
        movimiento.setIsValid(true);
    
        // Actualizar el saldo de la cuenta
        cuenta.setSaldo(cuenta.getSaldo() + movimiento.getImporteMovimiento());
        
        // Agregar el movimiento a la cuenta usando el método helper
        cuenta.addMovimiento(movimiento);
    
        // Guardar la cuenta actualizada
        Cuenta cuentaActualizada = cuentaRepository.save(cuenta);
    
        // Retornar el DTO de respuesta
        return cuentaMapper.cuentaToResponseAddMovimientoDto(cuentaActualizada);
    }

    
    public Long calcularNuevoSaldo(Long saldoActual, Movimiento movimiento) {
        // if (movimiento.getTipoMovimiento() == TipoMovimiento.INGRESO) {
        //     return saldoActual + movimiento.getMonto();
        // } else if (movimiento.getTipoMovimiento() == TipoMovimiento.EGRESO) {
        //     if (saldoActual < movimiento.getMonto()) {
        //         throw new IllegalStateException("Saldo insuficiente para realizar el movimiento");
        //     }
        //     return saldoActual - movimiento.getMonto();
        // }
        // throw new IllegalArgumentException("Tipo de movimiento no válido");
        return null;
    }


}
