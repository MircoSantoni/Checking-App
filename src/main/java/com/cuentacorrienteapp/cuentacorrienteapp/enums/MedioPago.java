package com.cuentacorrienteapp.cuentacorrienteapp.enums;

public enum MedioPago {
    EFECTIVO("EFECTIVO"),
    TARJETA_DEBITO("TARJETA_DEBITO"),
    TRANSFERENCIA_BANCARIA("TRANSFERENCIA_BANCARIA"),
    CHEQUE_CORRIENTE("CHEQUE"),
    CHEQUE_DIFERIDO("CHEQUE_DIFERIDO"),
    PAGARE("PAGARE");

    private final String medioPago;

    MedioPago(String medioPago) {
        this.medioPago = medioPago;
    }

    public String getMedioPago() {
        return medioPago;
    }   
}