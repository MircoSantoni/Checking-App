package com.cuentacorrienteapp.cuentacorrienteapp.dtos.user;

public record ErrorResponse(
        int statusCode,
        String message
) {

}