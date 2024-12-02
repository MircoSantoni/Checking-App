package com.cuentacorrienteapp.cuentacorrienteapp.dtos.user;

public record ResponseLoginDto(
    String id,
    String token,
    Long expiresIn,
    String name,
    String surname, 
    String email
    ) {}
