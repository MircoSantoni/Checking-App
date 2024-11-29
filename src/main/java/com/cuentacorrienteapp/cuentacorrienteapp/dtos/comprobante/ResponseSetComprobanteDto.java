package com.cuentacorrienteapp.cuentacorrienteapp.dtos.comprobante;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.cuentacorrienteapp.cuentacorrienteapp.enums.TipoComprobante;

import lombok.Builder;

@Builder
public record ResponseSetComprobanteDto(
    String id,
    TipoComprobante tipoComprobante,
    Double montoComprobante,
    String descripcion,
    Long nroComprobante,
    LocalDate fechaComprobante,
    LocalDateTime fechaAltaComprobante,
    boolean isValid,
    String movimientoId

) { }
