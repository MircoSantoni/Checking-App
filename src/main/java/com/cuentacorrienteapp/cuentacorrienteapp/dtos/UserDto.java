package com.cuentacorrienteapp.cuentacorrienteapp.dtos;

public record UserDto(
    String id,
    String name,
    String email,
    String jwt
) {}
