package com.cuentacorrienteapp.cuentacorrienteapp.services;


import java.util.Set;

import com.cuentacorrienteapp.cuentacorrienteapp.dtos.comprobante.*;

public interface ComprobanteService {
    
    Set<ResponseSetComprobanteDto> findAll();
    ResponseComprobanteDto findById(String id);
    Set<ResponseSetComprobanteDto> findByMovimientoId(String id);
    ResponseComprobanteDto saveComprobante (RequestComprobanteDto requestComprobanteDto);
    ResponseComprobanteDto updateIsValid(String id);
    ResponseAsignMovimientoDto asignMovimiento(RequestAsignMovimientoDto requestAsignMovimientoDto);
}
