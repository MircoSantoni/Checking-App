package com.cuentacorrienteapp.cuentacorrienteapp.services.implementation;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cuentacorrienteapp.cuentacorrienteapp.dtos.movimiento.RequestCreateMovimientoDto;
import com.cuentacorrienteapp.cuentacorrienteapp.dtos.movimiento.ResponseCreateMovimientoDto;
import com.cuentacorrienteapp.cuentacorrienteapp.entities.Movimiento;
import com.cuentacorrienteapp.cuentacorrienteapp.mappers.MovimientoMapper;
import com.cuentacorrienteapp.cuentacorrienteapp.repositories.MovimientoRepository;
import com.cuentacorrienteapp.cuentacorrienteapp.services.MovimientoService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MovimientoServiceImpl implements MovimientoService{

    private final MovimientoRepository movimientoRepository; //Cambiar esto a DTOs

    private final MovimientoMapper movimientoMapper;


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


}
