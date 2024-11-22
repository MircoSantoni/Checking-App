package com.cuentacorrienteapp.cuentacorrienteapp.dtos.movimiento;

import java.time.LocalDateTime;
import java.util.Set;
import com.cuentacorrienteapp.cuentacorrienteapp.entities.Comprobante;

import lombok.Builder;

@Builder
public record ResponseActiveMovimientoDto(
    String id, 
    Long importeMovimiento,
    String medioPago,
    String comentarioMovimiento,
    LocalDateTime fechaAltaMovimiento,
    Boolean isValid,
    Set<Comprobante> comprobantes
) { }
