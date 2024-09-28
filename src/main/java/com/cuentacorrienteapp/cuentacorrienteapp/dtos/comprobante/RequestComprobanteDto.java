package com.cuentacorrienteapp.cuentacorrienteapp.dtos.comprobante;

import java.time.LocalDate;

public record RequestComprobanteDto(
String tipoComprobante,
String descripcion,
Long nroComprobante,
LocalDate fechaAltaComprobante,
LocalDate fechaBajaComprobante

) {}