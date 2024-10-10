package com.cuentacorrienteapp.cuentacorrienteapp.exceptions;

public record ErrorResponse(
        int statusCode,
        String message
) {

}