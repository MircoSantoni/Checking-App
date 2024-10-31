package com.cuentacorrienteapp.cuentacorrienteapp.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cuentacorrienteapp.cuentacorrienteapp.dtos.movimiento.*;
import com.cuentacorrienteapp.cuentacorrienteapp.services.MovimientoService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/movimientos")
public class MovimientoController {
    
    private final MovimientoService movimientoService;
    
    // falta ver todos los movimientos y ver todos los dados de baja

    
    @PostMapping("/crear-movimiento")
    public ResponseEntity<ResponseCreateMovimientoDto> create(@Valid @RequestBody RequestCreateMovimientoDto requestCreateMovimientoDto) {
        ResponseCreateMovimientoDto responseCreateMovimientoDto = movimientoService.save(requestCreateMovimientoDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseCreateMovimientoDto);
    }

    
    // agregarle movimientos a una cuenta 
    @PostMapping("/asignar-cuenta")
    public ResponseEntity<ResponsePutMovCuentaDto> putMovCuenta(@Valid RequestPutMovCuentaDto requestComprobanteDto) {
        ResponsePutMovCuentaDto responsePutMovCuentaDto = movimientoService.putCuenta(requestComprobanteDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(responsePutMovCuentaDto);
    }
    
    // cambiar estado
    // @PostMapping("/{id}")
    // public ResponseEntity<?> asignCuenta(@RequestBody @Valid RequestAsignMovCuen requestAsignMovCuen) {
    //     ResponseAsignMovCuen result = movimientoService.asignCuenta(requestAsignMovCuen);
    //     return ResponseEntity.status(HttpStatus.CREATED).body(result);
    // }



}