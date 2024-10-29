package com.cuentacorrienteapp.cuentacorrienteapp.dtos.comprobante;

import java.time.LocalDateTime;
import java.util.Set;

import com.cuentacorrienteapp.cuentacorrienteapp.entities.Movimiento;

import lombok.Builder;

@Builder
public record ResponseSetComprobanteDto(
    String id,
    String tipo_comprobante,
    String descripcion,
    Long nroComprobante,
    LocalDateTime fechaAltaComprobante,
    Set<Movimiento> movimiento


) { }
