package com.cuentacorrienteapp.cuentacorrienteapp.dtos.comprobante;

import jakarta.validation.constraints.NotNull;

public record RequestComprobanteDto(

@NotNull(message= "Colocar el tipo de comprobante es obligatorio")
String tipoComprobante,
@NotNull(message= "Colocar una descripcion de un comprobante es obligatorio")
String descripcion,
@NotNull(message="Colocar el numero del comprobante es obligatorio")
Long nroComprobante

) {}