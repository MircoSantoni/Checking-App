package com.cuentacorrienteapp.cuentacorrienteapp.exceptions;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class EntityNotFoundException extends RuntimeException{
    private String message;

}
