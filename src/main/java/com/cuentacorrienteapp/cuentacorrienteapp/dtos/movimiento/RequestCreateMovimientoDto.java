package com.cuentacorrienteapp.cuentacorrienteapp.dtos.movimiento;

import com.cuentacorrienteapp.cuentacorrienteapp.enums.MedioPago;

import jakarta.validation.constraints.NotNull;

public record RequestCreateMovimientoDto(
    @NotNull
    Long importeMovimiento,
    @NotNull(message = "El medio de pago debe de ser alguno de los siguientes EFECTIVO, TARJETA_DEBITO, TRANSFERENCIA_BANCARIA, CHEQUE_CORRIENTE, CHEQUE_DIFERIDO, PAGARE")
    MedioPago medioPago,
    @NotNull
    String comentarioMovimiento,
    @NotNull
    String cuentaId

    ) {}
