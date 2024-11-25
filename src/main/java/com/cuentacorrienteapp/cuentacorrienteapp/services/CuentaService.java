package com.cuentacorrienteapp.cuentacorrienteapp.services;

import java.util.*;

import com.cuentacorrienteapp.cuentacorrienteapp.dtos.cuenta.*;
import com.cuentacorrienteapp.cuentacorrienteapp.entities.Movimiento;

public interface CuentaService {

    Set<ResponseCuentaDto> findAll();
    ResponseCuentaDto findById(String id);
    ResponseCuentaDto save(RequestCuentaDto requestCuentaDto);
    ResponseUpdateIsValidDto updateIsValid(String id);
    ResponseAddMovimientoDto addMovimientoToCuenta(RequestAddMovimientoDto requestAddMovimientoDto);
    Long calcularNuevoSaldo (Long saldoActual, Movimiento movimiento);

}