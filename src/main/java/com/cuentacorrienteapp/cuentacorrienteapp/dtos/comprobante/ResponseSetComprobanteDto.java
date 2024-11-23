package com.cuentacorrienteapp.cuentacorrienteapp.dtos.comprobante;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.Builder;

@Builder
public record ResponseSetComprobanteDto(
    String id,
    String tipoComprobante,
    Double montoComprobante,
    String descripcion,
    Long nroComprobante,
    LocalDate fechaComprobante,
    LocalDateTime fechaAltaComprobante,
    boolean isValid,
    String movimientoId

) { }
