package com.cuentacorrienteapp.cuentacorrienteapp.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cuentacorrienteapp.cuentacorrienteapp.services.ComprobanteService;

import com.cuentacorrienteapp.cuentacorrienteapp.dtos.comprobante.*;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;


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
    @GetMapping("/b-id/{id}")
    public ResponseEntity<ResponseComprobanteDto> viewId(@Valid @PathVariable String id) {
        ResponseComprobanteDto result = comprobanteService.findById(id);
        return ResponseEntity.ok(result);
    }

    // mostrar comprobante segun numero de comprobante 
    @GetMapping("b-numero-comprobante/{nroComprobante}")
    public ResponseEntity<ResponseComprobanteDto> viewNroCompr(@Valid @PathVariable Long nroComprobante) {
        ResponseComprobanteDto result = comprobanteService.findByNroComprobante(nroComprobante);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/guardar-comprobante")
    public ResponseEntity<ResponseComprobanteDto> saveComprobante(@Valid @RequestBody RequestComprobanteDto requestComprobanteDto) {
        ResponseComprobanteDto result = comprobanteService.saveComprobante(requestComprobanteDto);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/cambiar-estado/{nroComprobante}")
    public ResponseEntity<ResponseComprobanteDto> updateState(@Valid @PathVariable Long nroComprobante ) {
        ResponseComprobanteDto result = comprobanteService.updateIsValid(nroComprobante);
        return ResponseEntity.ok(result);
    }
    
}
