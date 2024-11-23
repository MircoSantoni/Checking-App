package com.cuentacorrienteapp.cuentacorrienteapp.dtos.comprobante;

public record RequestAsignMovimientoDto(
    String idComprobante,
    String idMovimiento
) { }