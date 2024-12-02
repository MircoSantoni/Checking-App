package com.cuentacorrienteapp.cuentacorrienteapp.dtos.movimiento;

import java.time.LocalDateTime;
import java.util.Set;

import com.cuentacorrienteapp.cuentacorrienteapp.entities.Comprobante;
import com.cuentacorrienteapp.cuentacorrienteapp.enums.MedioPago;

import lombok.Builder;

@Builder
public record ResponseMovimientoDto(
    String id, 
    Long importeMovimiento,
    Long importePagado,
    MedioPago medioPago,
    String comentarioMovimiento,
    LocalDateTime fechaAltaMovimiento,
    String cuentaId,
    Set<Comprobante> comprobantes,
    Boolean isValid
    ) { }
