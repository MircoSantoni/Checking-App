package com.cuentacorrienteapp.cuentacorrienteapp.dtos.cuenta;

public record ResponseAddMovimientoDto(
    String cuentaId,
    String movimientoId,
    String nombreCuenta
) { }