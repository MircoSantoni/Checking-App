package com.cuentacorrienteapp.cuentacorrienteapp.dtos.comprobante;

import java.time.LocalDate;

import lombok.Builder;

@Builder
public record ResponseComprobanteDto(
    String id,
    String tipoComprobante,
    Long nroComprobante,
    Double montoComprobante,
    LocalDate fechaComprobante
) {}
