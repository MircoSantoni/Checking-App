package com.cuentacorrienteapp.cuentacorrienteapp.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class InvalidDataException extends RuntimeException {
    private String message;
}
