package com.cuentacorrienteapp.cuentacorrienteapp.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cuentacorrienteapp.cuentacorrienteapp.services.ComprobanteService;

import com.cuentacorrienteapp.cuentacorrienteapp.dtos.comprobante.*;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/comprobantes")
public class ComprobanteController {

    private final ComprobanteService comprobanteService;

    // mostrar comprobantes segun id
    @GetMapping("/mostrar-id/{id}")
    public ResponseEntity<ResponseComprobanteDto> viewId(@Valid @PathVariable String id) {
        ResponseComprobanteDto result = comprobanteService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping("/mostrar-comprobante/{id}")
    public ResponseEntity<Set<ResponseSetComprobanteDto>> viewNroCompr(@Valid @PathVariable String id) {
        Set<ResponseSetComprobanteDto> result = comprobanteService.findByMovimientoId(id);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    //mostrar comprobantes segun movimiento asignado
    @GetMapping("/mostrar")
    public ResponseEntity<Set<ResponseSetComprobanteDto>> viewComprobante() {
        Set<ResponseSetComprobanteDto> result = comprobanteService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    // crear comprobante
    @PostMapping("/guardar")
    public ResponseEntity<ResponseComprobanteDto> saveComprobante(@Valid @RequestBody RequestComprobanteDto requestComprobanteDto) {
        ResponseComprobanteDto result = comprobanteService.saveComprobante(requestComprobanteDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }
    
    // updatear el estado de un comprobante
    @PostMapping("/cambiar-estado/{id}")
    public ResponseEntity<ResponseComprobanteDto> updateState(@Valid @PathVariable String id ) {
        ResponseComprobanteDto result = comprobanteService.updateIsValid(id);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }
    
}
