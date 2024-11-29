package com.cuentacorrienteapp.cuentacorrienteapp.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class MontException extends RuntimeException{
    private String message;
}
