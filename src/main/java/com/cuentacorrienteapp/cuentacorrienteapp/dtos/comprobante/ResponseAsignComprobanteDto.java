package com.cuentacorrienteapp.cuentacorrienteapp.dtos.comprobante;

public record ResponseAsignComprobanteDto(
    String idComprobante,
    String idMovimiento,
    String descripcion
) { }
