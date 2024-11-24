package com.cuentacorrienteapp.cuentacorrienteapp.controllers;

import java.util.*;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cuentacorrienteapp.cuentacorrienteapp.dtos.cuenta.*;

import com.cuentacorrienteapp.cuentacorrienteapp.services.CuentaService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/cuentas")
@RequiredArgsConstructor
public class CuentaController {

    private final CuentaService cuentaService;

    @GetMapping("/ver-cuentas")
    public ResponseEntity<List<ResponseCuentaDto>> viewCuentas() {
        List<ResponseCuentaDto> result = cuentaService.findAll();
        return ResponseEntity.ok(result);
    }
    
    @GetMapping("/ver-cuenta-id/{id}")
    public ResponseEntity<ResponseCuentaDto> viewId( @PathVariable String id) {
        ResponseCuentaDto result = cuentaService.findById(id);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/crear-cuenta")
    public ResponseEntity<ResponseCuentaDto> saveCuenta(@Valid @RequestBody RequestCuentaDto requestCuentaDto){
        ResponseCuentaDto result = cuentaService.save(requestCuentaDto);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/cambiar-estado/{idCuenta}")
    public ResponseEntity<ResponseUpdateIsValidDto> changeState( @PathVariable String idCuenta) {
        ResponseUpdateIsValidDto result = cuentaService.updateIsValid(idCuenta);
        return ResponseEntity.ok(result);
    }

}
