package com.cuentacorrienteapp.cuentacorrienteapp.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = FechaValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidFecha {
    String message() default "La fecha debe estar en formato yyyy-MM-dd";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
