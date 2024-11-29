package com.cuentacorrienteapp.cuentacorrienteapp.enums;

public enum TipoComprobante {
        FACTURA("FACTURA"),
        NOTA_DEBITO("NOTA_DEBITO"),
        NOTA_CREDITO("NOTA_CREDITO"),
        RECIBO("RECIBO"),
        REMITO("REMITO"),
        TICKET("TICKET");

    private final String tipoComprobante;

    TipoComprobante(String tipoComprobante) {
        this.tipoComprobante = tipoComprobante;
    }

    public String getTipoComprobante() {
        return tipoComprobante;
    }
}
