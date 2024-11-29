package com.cuentacorrienteapp.cuentacorrienteapp.services.implementation;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cuentacorrienteapp.cuentacorrienteapp.dtos.movimiento.*;
import com.cuentacorrienteapp.cuentacorrienteapp.entities.Cuenta;
import com.cuentacorrienteapp.cuentacorrienteapp.entities.Movimiento;
import com.cuentacorrienteapp.cuentacorrienteapp.mappers.MovimientoMapper;
import com.cuentacorrienteapp.cuentacorrienteapp.repositories.CuentaRepository;
import com.cuentacorrienteapp.cuentacorrienteapp.repositories.MovimientoRepository;
import com.cuentacorrienteapp.cuentacorrienteapp.services.MovimientoService;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MovimientoServiceImpl implements MovimientoService {

    private final MovimientoRepository movimientoRepository;
    private final CuentaRepository cuentaRepository;
    private final MovimientoMapper movimientoMapper;

    @Override
    @Transactional(readOnly = true)
    public Set<ResponseMovimientoDto> findAll() {
        try {
            return movimientoRepository.findAll().stream()
                .<ResponseMovimientoDto>map(movimiento -> ResponseMovimientoDto.builder()
                    .id(movimiento.getId())
                    .importeMovimiento(movimiento.getImporteMovimiento())
                    .medioPago(movimiento.getMedioPago())
                    .comentarioMovimiento(movimiento.getComentarioMovimiento())
                    .fechaAltaMovimiento(movimiento.getFechaAltaMovimiento())
                    .cuentaId(movimiento.getCuenta() != null ? movimiento.getCuenta().getId() : null)
                    .comprobantes(movimiento.getComprobantes())
                    .isValid(movimiento.getIsValid())
                    .build())
                .collect(Collectors.toSet());
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener los movimientos", e);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Set<ResponseActiveMovimientoDto> findAllActive() {
        try {
            return movimientoRepository.findAll().stream()
                .filter(movimiento -> movimiento.getIsValid() == null || movimiento.getIsValid())
                .<ResponseActiveMovimientoDto>map(movimiento -> ResponseActiveMovimientoDto.builder()
                    .id(movimiento.getId())
                    .importeMovimiento(movimiento.getImporteMovimiento())
                    .medioPago(movimiento.getMedioPago())
                    .comentarioMovimiento(movimiento.getComentarioMovimiento())
                    .fechaAltaMovimiento(movimiento.getFechaAltaMovimiento())
                    .cuentaId(movimiento.getCuenta() != null ? movimiento.getCuenta().getId() : null)
                    .comprobantes(movimiento.getComprobantes())
                    .isValid(movimiento.getIsValid())
                    .build())
                .collect(Collectors.toSet());
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener los movimientos activos", e);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseMovimientoDto findOne(String id) {
        Movimiento movimiento = movimientoRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Movimiento no encontrado"));
        return movimientoMapper.movimientoToResponseMovimientoDto(movimiento);
    }

    @Override
    @Transactional
    public ResponseCreateMovimientoDto save(RequestCreateMovimientoDto requestCreateMovimientoDto) {
        if (requestCreateMovimientoDto == null) {
            throw new IllegalArgumentException("El request no puede ser null");
        }

        Movimiento movimiento = movimientoMapper.requestCreateMovimientoDtoToMovimiento(requestCreateMovimientoDto);

        Cuenta cuenta = cuentaRepository.findById(requestCreateMovimientoDto.cuentaId())
                .orElseThrow(() -> new EntityNotFoundException("Cuenta no encontrada"));
        movimiento.setCuenta(cuenta);

        movimiento.setIsValid(true);
        Movimiento savedMovimiento = movimientoRepository.save(movimiento);
        return movimientoMapper.movimientoToResponseCreateMovimientoDto(savedMovimiento);
    }

    @Override
    @Transactional
    public ResponseUpdateMovimientoStateDto changeState(String id) {
        Movimiento movimiento = movimientoRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Movimiento no encontrado"));

        movimiento.setIsValid(!movimiento.getIsValid());
        if (!movimiento.getIsValid()) {
            ZoneId zoneId = ZoneId.of("America/Argentina/Buenos_Aires");
            movimiento.setFechaBajaMovimiento(ZonedDateTime.now(zoneId).toLocalDateTime());
        } else {
            movimiento.setFechaBajaMovimiento(null);
        }
        
        movimientoRepository.save(movimiento);
        return movimientoMapper.movimientoToResponseUpdateMovimientoStateDto(movimiento);
    }

}