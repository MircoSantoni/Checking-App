package com.cuentacorrienteapp.cuentacorrienteapp.dtos.comprobante;

import jakarta.validation.constraints.NotNull;

public record RequestAsignComprobanteDto(
    @NotNull
    String idComprobante,
    @NotNull
    String idMovimiento
) { }
