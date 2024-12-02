package com.cuentacorrienteapp.cuentacorrienteapp.dtos.movimiento;

import jakarta.validation.constraints.NotNull;

public record RequestImporteDto(
    @NotNull
    String id,
    @NotNull
    Long importePagado
) { }
