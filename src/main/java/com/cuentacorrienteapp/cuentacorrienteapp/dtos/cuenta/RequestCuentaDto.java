package com.cuentacorrienteapp.cuentacorrienteapp.dtos.cuenta;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record RequestCuentaDto(
    @NotBlank
    @Size(min=0 , max= 256)
    Long saldo,
    @NotNull
    String name
) {}