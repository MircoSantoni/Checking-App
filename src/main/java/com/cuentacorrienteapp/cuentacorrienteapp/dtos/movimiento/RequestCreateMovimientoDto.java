package com.cuentacorrienteapp.cuentacorrienteapp.dtos.movimiento;

import jakarta.validation.constraints.NotNull;

public record RequestCreateMovimientoDto(
    @NotNull
    Long importeMovimiento,
    @NotNull
    String medioPago,
    @NotNull
    String comentarioMovimiento,
    @NotNull
    String cuentaId

    ) {}
