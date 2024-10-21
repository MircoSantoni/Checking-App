package com.cuentacorrienteapp.cuentacorrienteapp.dtos.movimiento;

import com.cuentacorrienteapp.cuentacorrienteapp.entities.Comprobante;
import com.cuentacorrienteapp.cuentacorrienteapp.entities.Cuenta;

public record MovimientoDto(
    String id,
    Long importeMovimiento,
    String medioPago,
    String comentarioMovimiento,
    Comprobante comprobante,
    Cuenta cuenta

) {}