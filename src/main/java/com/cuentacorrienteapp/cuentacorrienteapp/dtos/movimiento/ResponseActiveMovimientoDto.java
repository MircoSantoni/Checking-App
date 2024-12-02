package com.cuentacorrienteapp.cuentacorrienteapp.dtos.movimiento;

import java.time.LocalDateTime;
import java.util.Set;
import com.cuentacorrienteapp.cuentacorrienteapp.entities.Comprobante;
import com.cuentacorrienteapp.cuentacorrienteapp.enums.MedioPago;

import lombok.Builder;

@Builder
public record ResponseActiveMovimientoDto(
    String id, 
    Long importeMovimiento,
    String importePagado,
    MedioPago medioPago,
    String comentarioMovimiento,
    LocalDateTime fechaAltaMovimiento,
    Boolean isValid,
    String cuentaId,
    Set<Comprobante> comprobantes
) { }
