package com.cuentacorrienteapp.cuentacorrienteapp.services.implementation;

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
public class MovimientoServiceImpl implements MovimientoService{

    private final MovimientoRepository movimientoRepository; 

    private final CuentaRepository cuentaRepository;

    private final MovimientoMapper movimientoMapper;


    @Override
    @Transactional(readOnly = true)
    public Set<ResponseMovimientoDto> findAll() {
        try {
            return movimientoRepository.findAll().stream()
                .<ResponseMovimientoDto>map(movimiento -> ResponseMovimientoDto.builder()
                .id(movimiento.getId()  )
                .importeMovimiento(movimiento.getImporteMovimiento())
                .medioPago(movimiento.getMedioPago())
                .comentarioMovimiento(movimiento.getComentarioMovimiento())
                .fechaAltaMovimiento(movimiento.getFechaAltaMovimiento())
                .fechaBajaMovimiento(movimiento.getFechaBajaMovimiento())
                .comprobantes(movimiento.getComprobantes())
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
                .<ResponseActiveMovimientoDto>map(movimiento -> ResponseActiveMovimientoDto.builder()
                .id(movimiento.getId()  )
                .importeMovimiento(movimiento.getImporteMovimiento())
                .medioPago(movimiento.getMedioPago())
                .comentarioMovimiento(movimiento.getComentarioMovimiento())
                .fechaAltaMovimiento(movimiento.getFechaAltaMovimiento())
                .comprobantes(movimiento.getComprobantes())
                .build())
                .collect(Collectors.toSet());
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener los movimientos", e);
        }
    }

    @Override
    @Transactional
    public ResponseCreateMovimientoDto save(RequestCreateMovimientoDto request) {
        if (request == null) {
            throw new IllegalArgumentException("El request no puede ser null");
        } else {
            Movimiento movimiento = movimientoMapper.requestCreateMovimientoDtoToMovimiento(request);
            Movimiento savedMovimiento = movimientoRepository.save(movimiento);

            return movimientoMapper.movimientoToResponseCreateMovimientoDto(savedMovimiento);

        }
    }  

    @Override
    @Transactional
    public ResponsePutMovCuentaDto putCuenta(RequestPutMovCuentaDto requestPutMovCuentaDto) {
        Movimiento movimiento = movimientoRepository.findById(requestPutMovCuentaDto.id_movimiento()).orElseThrow(() -> new EntityNotFoundException("Este movimiento no existe"));
        Cuenta cuenta = cuentaRepository.findById(requestPutMovCuentaDto.id_cuenta()).orElseThrow(() -> new EntityNotFoundException("Cuenta con ID %d no encontrada"));
        
        movimiento.setCuenta(cuenta);
        
        Movimiento savedMovimiento = movimientoRepository.save(movimiento);
        return movimientoMapper.movimientoToResponsePutMovCuentaDto(savedMovimiento);
        }


}
