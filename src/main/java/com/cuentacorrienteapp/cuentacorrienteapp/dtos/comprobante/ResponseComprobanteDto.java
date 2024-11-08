package com.cuentacorrienteapp.cuentacorrienteapp.dtos.comprobante;

import java.time.LocalDate;

public record ResponseComprobanteDto(
    String id,
    String tipoComprobante,
    Long nroComprobante,
    LocalDate fechaComprobante
) {}
