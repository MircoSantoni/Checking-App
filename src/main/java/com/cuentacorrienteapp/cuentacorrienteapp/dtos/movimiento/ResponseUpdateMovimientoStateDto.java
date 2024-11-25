package com.cuentacorrienteapp.cuentacorrienteapp.dtos.movimiento;

import java.time.LocalDateTime;

public record ResponseUpdateMovimientoStateDto(    
    String id,
    Long importeMovimiento,
    String comentarioMovimiento,
    LocalDateTime fechaBajaMovimiento
    ) { }
