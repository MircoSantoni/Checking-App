package com.cuentacorrienteapp.cuentacorrienteapp.validation;

import org.springframework.stereotype.Component;
import org.springframework.validation.Validator;

@Component
public class ValidationClass implements Validator {

    @Override
    public boolean supports(Class<?> clazz){
        return validable.class.isAssignableFrom(clazz);
    }

}
