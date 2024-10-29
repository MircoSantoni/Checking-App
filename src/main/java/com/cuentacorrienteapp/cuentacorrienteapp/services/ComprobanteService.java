package com.cuentacorrienteapp.cuentacorrienteapp.services;


import java.util.Set;

import com.cuentacorrienteapp.cuentacorrienteapp.dtos.comprobante.*;

public interface ComprobanteService {
    
    Set<ResponseSetComprobanteDto> findAll();
    ResponseComprobanteDto findById(String id);
    ResponseComprobanteDto findByNroComprobante(Long nroComprobante);
    ResponseComprobanteDto saveComprobante (RequestComprobanteDto requestComprobanteDto);
    ResponseComprobanteDto updateIsValid(String id);
    ResponseAsignComprobanteDto asignMovimiento(RequestAsignComprobanteDto requestAsignComprobanteDto);
}
