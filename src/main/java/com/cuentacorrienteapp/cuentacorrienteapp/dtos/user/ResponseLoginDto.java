package com.cuentacorrienteapp.cuentacorrienteapp.dtos.user;

public record ResponseLoginDto(
    String id,
    String cuentaId,
    String token,
    Long expiresIn
    ) {}
