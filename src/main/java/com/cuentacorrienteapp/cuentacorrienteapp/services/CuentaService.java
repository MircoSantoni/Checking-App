package com.cuentacorrienteapp.cuentacorrienteapp.services;

import java.util.*;

import com.cuentacorrienteapp.cuentacorrienteapp.dtos.cuenta.*;

public interface CuentaService {

    List<ResponseCuentaDto> findAll();
    ResponseCuentaDto findById(String id);
    ResponseCuentaDto save(RequestCuentaDto requestCuentaDto);
    ResponseUpdateIsValidDto updateIsValid(String id);

}