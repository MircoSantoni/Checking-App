package com.cuentacorrienteapp.cuentacorrienteapp.dtos.cuenta;

import jakarta.validation.constraints.NotNull;

public record RequestAddMovimientoDto(
    @NotNull(message = "El id de cuenta es requerido")
    String cuentaId,
    @NotNull(message = "El id de movimiento es requerido")
    String movimientoId
)
{ }
