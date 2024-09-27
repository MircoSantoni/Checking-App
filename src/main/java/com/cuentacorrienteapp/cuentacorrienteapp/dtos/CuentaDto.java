package com.cuentacorrienteapp.cuentacorrienteapp.dtos;

import com.cuentacorrienteapp.cuentacorrienteapp.entities.Movimiento;

public record CuentaDto(
    String id,
    Long saldo,
    String name,
    Movimiento movimiento
) {}