package com.cuentacorrienteapp.cuentacorrienteapp.services;

import java.util.List;
import java.util.Optional;

import com.cuentacorrienteapp.cuentacorrienteapp.dtos.comprobante.*;

public interface ComprobanteService {

    ResponseComprobanteDto findById(String id);
    ResponseComprobanteDto findByNroComprobante(Long nroComprobante);
    ResponseComprobanteDto saveComprobante (RequestComprobanteDto requestComprobanteDto);
    ResponseComprobanteDto updateIsValid(String id);

}
