package com.cuentacorrienteapp.cuentacorrienteapp.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cuentacorrienteapp.cuentacorrienteapp.dtos.user.RequestLoginDto;
import com.cuentacorrienteapp.cuentacorrienteapp.dtos.user.RequestRegisterDto;
import com.cuentacorrienteapp.cuentacorrienteapp.dtos.user.ResponseLoginDto;
import com.cuentacorrienteapp.cuentacorrienteapp.dtos.user.ResponseRegisterDto;
import com.cuentacorrienteapp.cuentacorrienteapp.services.AuthenticationService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/autenticacion")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/registro")
    public ResponseEntity<ResponseRegisterDto> registerUser(@Valid @RequestBody RequestRegisterDto requestRegisterDto) {
        ResponseRegisterDto responseRegisterDto = authenticationService.signUp(requestRegisterDto);
        return new ResponseEntity<>(responseRegisterDto, HttpStatus.CREATED);
    }

    @PostMapping("/inicio-sesion")
    public ResponseEntity<ResponseLoginDto> loginUser(@Valid @RequestBody RequestLoginDto requestLoginDto) {
        ResponseLoginDto responseLoginDto = authenticationService.login(requestLoginDto);
        return new ResponseEntity<>(responseLoginDto, HttpStatus.OK);
    }
}
