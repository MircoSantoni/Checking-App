package com.cuentacorrienteapp.cuentacorrienteapp.enums;

public enum EstadoCuenta {
    MOROSA("MOROSA"),
    PENDIENTE("PENDIENTE"),
    CANCELADA("CANCELADA"),
    ;

    private final String estadoCuenta;

    EstadoCuenta(String estadoCuenta) {
        this.estadoCuenta = estadoCuenta;
    }

    public String getEstadoCuenta() {
        return estadoCuenta;
    }
}