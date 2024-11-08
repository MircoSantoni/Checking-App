package com.cuentacorrienteapp.cuentacorrienteapp.dtos.comprobante;

import java.time.LocalDate;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record RequestComprobanteDto(

@NotNull(message= "Colocar el tipo de comprobante es obligatorio")
String tipoComprobante,
@NotNull(message= "Colocar una descripcion de un comprobante es obligatorio")
String descripcion,
@NotNull(message="Colocar el numero del comprobante es obligatorio")
Long nroComprobante,
@NotNull(message ="Colocar la fecha del comprobante es obligatorio ")
@Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "La fecha debe estar en el formato yyyy-MM-dd")
LocalDate fechaComprobante
) {}