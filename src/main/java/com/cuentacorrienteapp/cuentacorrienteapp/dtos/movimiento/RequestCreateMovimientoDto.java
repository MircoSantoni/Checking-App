package com.cuentacorrienteapp.cuentacorrienteapp.dtos.movimiento;

public record RequestCreateMovimientoDto(
    Long importeMovimiento,
    String medioPago,
    String comentarioMovimiento
    ) {}
