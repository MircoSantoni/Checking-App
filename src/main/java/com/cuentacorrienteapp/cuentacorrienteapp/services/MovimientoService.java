package com.cuentacorrienteapp.cuentacorrienteapp.services;


import com.cuentacorrienteapp.cuentacorrienteapp.dtos.movimiento.RequestCreateMovimientoDto;
import com.cuentacorrienteapp.cuentacorrienteapp.dtos.movimiento.RequestPutMovCuentaDto;
import com.cuentacorrienteapp.cuentacorrienteapp.dtos.movimiento.ResponseCreateMovimientoDto;
import com.cuentacorrienteapp.cuentacorrienteapp.dtos.movimiento.ResponsePutMovCuentaDto;

public interface MovimientoService {

    ResponseCreateMovimientoDto save(RequestCreateMovimientoDto requestCreateMovimientoDto);

    ResponsePutMovCuentaDto putCuenta(RequestPutMovCuentaDto requestPutMovCuentaDto);
}
