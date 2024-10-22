package com.cuentacorrienteapp.cuentacorrienteapp.services;

import java.util.List;
import java.util.Optional;

import com.cuentacorrienteapp.cuentacorrienteapp.dtos.movimiento.RequestCreateMovimientoDto;
import com.cuentacorrienteapp.cuentacorrienteapp.dtos.movimiento.ResponseCreateMovimientoDto;
import com.cuentacorrienteapp.cuentacorrienteapp.entities.Movimiento;

public interface MovimientoService {

    ResponseCreateMovimientoDto save(RequestCreateMovimientoDto requestCreateMovimientoDto);


}
