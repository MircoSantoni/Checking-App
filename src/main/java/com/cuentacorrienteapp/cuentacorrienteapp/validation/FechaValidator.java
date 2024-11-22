package com.cuentacorrienteapp.cuentacorrienteapp.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.time.LocalDate;

public class FechaValidator implements ConstraintValidator<ValidFecha, LocalDate> {

    @Override
    public boolean isValid(LocalDate value, ConstraintValidatorContext context) {
        return value != null; // Si querés más lógica, podés agregarla aquí.
    }
}
