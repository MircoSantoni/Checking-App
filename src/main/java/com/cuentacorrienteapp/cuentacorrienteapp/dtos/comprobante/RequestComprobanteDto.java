package com.cuentacorrienteapp.cuentacorrienteapp.dtos.comprobante;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RequestComprobanteDto(
@NotBlank
String tipoComprobante,
@NotBlank
@Size( min=0 , max=256)
String descripcion,
@NotBlank
Long nroComprobante,
LocalDate fechaAltaComprobante,
LocalDate fechaBajaComprobante

) {}