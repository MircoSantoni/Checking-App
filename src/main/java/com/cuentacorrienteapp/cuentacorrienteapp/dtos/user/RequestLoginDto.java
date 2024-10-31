package com.cuentacorrienteapp.cuentacorrienteapp.dtos.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RequestLoginDto(
    @NotBlank( message="La contraseña es obligatoria para iniciar sesion")
    String password,
    @NotNull( message="El email es obligatorio para iniciar sesion")
    String email,
    @NotNull( message="El cuit es obligatorio para iniciar sesion")
    String cuit
) {}
