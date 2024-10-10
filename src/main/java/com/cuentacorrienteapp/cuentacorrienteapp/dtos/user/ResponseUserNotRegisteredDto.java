package com.cuentacorrienteapp.cuentacorrienteapp.dtos.user;

public record ResponseUserNotRegisteredDto(
    String id,
    String name,
    String surname,
    String email,
    String phone
) {}
