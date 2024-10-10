package com.cuentacorrienteapp.cuentacorrienteapp.services;

import com.cuentacorrienteapp.cuentacorrienteapp.dtos.user.*;

import jakarta.validation.Valid;

public interface AuthenticationService {

    ResponseRegisterDto signUp(@Valid RequestRegisterDto requestRegisterDto);
    ResponseLoginDto login(RequestLoginDto requestLoginDto);
    
}