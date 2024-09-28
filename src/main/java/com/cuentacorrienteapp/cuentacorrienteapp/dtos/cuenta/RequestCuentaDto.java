package com.cuentacorrienteapp.cuentacorrienteapp.dtos.cuenta;

public record RequestCuentaDto(
    Long saldo,
    String name
) {}