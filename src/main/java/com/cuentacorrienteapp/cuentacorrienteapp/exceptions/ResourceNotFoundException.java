package com.cuentacorrienteapp.cuentacorrienteapp.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ResourceNotFoundException extends RuntimeException{
private String message;
}
