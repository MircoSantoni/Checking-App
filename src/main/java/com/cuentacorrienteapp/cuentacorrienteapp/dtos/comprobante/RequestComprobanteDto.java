package com.cuentacorrienteapp.cuentacorrienteapp.dtos.comprobante;

import java.time.LocalDate;

import com.cuentacorrienteapp.cuentacorrienteapp.validation.ValidFecha;

import jakarta.validation.constraints.NotNull;

public record RequestComprobanteDto(

@NotNull(message= "Colocar el tipo de comprobante es obligatorio")
String tipoComprobante,
@NotNull(message= "Colocar una descripcion de un comprobante es obligatorio")
String descripcion,
@NotNull(message="Colocar el numero del comprobante es obligatorio")
Long nroComprobante,
@NotNull(message ="Colocar la fecha del comprobante es obligatorio ")
@ValidFecha
LocalDate fechaComprobante,
@NotNull(message ="Colocar el monto del comprobante es obligatorio")
Double montoComprobante
) {}