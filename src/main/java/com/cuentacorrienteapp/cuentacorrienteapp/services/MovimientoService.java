package com.cuentacorrienteapp.cuentacorrienteapp.services;

import java.util.Set;

import com.cuentacorrienteapp.cuentacorrienteapp.dtos.movimiento.*;

public interface MovimientoService {

    Set<ResponseMovimientoDto> findAll();
    
    Set<ResponseActiveMovimientoDto> findAllActive();

    ResponseMovimientoDto findOne(String id);

    ResponseCreateMovimientoDto save(RequestCreateMovimientoDto requestCreateMovimientoDto);

    ResponseUpdateMovimientoStateDto changeState(String id);
}