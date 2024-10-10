package com.cuentacorrienteapp.cuentacorrienteapp.dtos.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RequestLoginDto(
    @NotBlank
    String password,
    @NotNull
    String email
) {}
