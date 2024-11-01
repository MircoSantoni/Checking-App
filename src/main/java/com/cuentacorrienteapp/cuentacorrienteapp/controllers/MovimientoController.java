package com.cuentacorrienteapp.cuentacorrienteapp.controllers;

import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cuentacorrienteapp.cuentacorrienteapp.dtos.movimiento.*;
import com.cuentacorrienteapp.cuentacorrienteapp.services.MovimientoService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/movimientos")
public class MovimientoController {
    
    private final MovimientoService movimientoService;
    
    
    // traer todos los movimientos 
    @GetMapping("/ver")
    public ResponseEntity<Set<ResponseActiveMovimientoDto>> getAllActiveMovimientos() {
        Set<ResponseActiveMovimientoDto> movimientos = movimientoService.findAllActive();
        return ResponseEntity.ok(movimientos);
    }
    
    // traer todos los movimientos incluidos los dados de baja
    @GetMapping("/ver-todos")
    public ResponseEntity<Set<ResponseMovimientoDto>> getAllMovimientos() {
        Set<ResponseMovimientoDto> movimientos = movimientoService.findAll();
        return ResponseEntity.ok(movimientos);
    }
    
    // crear un movimiento
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
    @PostMapping("/cambiar-estado/{id}")
    public ResponseEntity<?> asignCuenta(@PathVariable String id) {
        ResponseMovimientoDto result = movimientoService.changeState(id);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

}