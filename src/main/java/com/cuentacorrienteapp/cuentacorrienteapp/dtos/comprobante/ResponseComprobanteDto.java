package com.cuentacorrienteapp.cuentacorrienteapp.dtos.comprobante;

public record ResponseComprobanteDto(
    String id,
    String tipoComprobante,
    Long nroComprobante
) {}
