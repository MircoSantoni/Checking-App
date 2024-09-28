package com.cuentacorrienteapp.cuentacorrienteapp.dtos.cuenta;

public record ResponseCuentaDto(
    String id,
    Long saldo,
    String name
) {}
