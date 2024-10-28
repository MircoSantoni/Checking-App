package com.cuentacorrienteapp.cuentacorrienteapp.dtos.user;

public record ResponseLoginDto(
    String id,
    String token,
    Long expiresIn
    ) {}
