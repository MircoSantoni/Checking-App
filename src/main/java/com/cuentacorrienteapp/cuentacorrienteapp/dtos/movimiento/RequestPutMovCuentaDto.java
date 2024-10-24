package com.cuentacorrienteapp.cuentacorrienteapp.dtos.movimiento;

public record RequestPutMovCuentaDto(
    String id_cuenta,
    String id_movimiento
) { }
