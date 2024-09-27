package com.cuentacorrienteapp.cuentacorrienteapp.dtos.comprobante;

import com.cuentacorrienteapp.cuentacorrienteapp.entities.Movimiento;

public record RequestComprobanteDto (
    String id,
    String tipoComprobante,
    String descripcion,
    Long nroComprobante,
    Movimiento movimientos

) 
{}
