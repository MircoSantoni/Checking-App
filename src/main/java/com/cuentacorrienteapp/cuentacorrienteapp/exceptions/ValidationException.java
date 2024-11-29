package com.cuentacorrienteapp.cuentacorrienteapp.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ValidationException extends RuntimeException{
    public ValidationException(String message) {
        super(message);
    }}
