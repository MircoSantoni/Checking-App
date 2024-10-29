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
    @GetMapping("/mostrar-id/{id}")
    public ResponseEntity<ResponseComprobanteDto> viewId(@Valid @PathVariable String id) {
        ResponseComprobanteDto result = comprobanteService.findById(id);
        return ResponseEntity.ok(result);
    }

    // mostrar comprobantes segun numero de comprobante este debe de devolvver una lista pajin
    @GetMapping("/mostrar-comprobante/{nroComprobante}")
    public ResponseEntity<ResponseComprobanteDto> viewNroCompr(@Valid @PathVariable Long nroComprobante) {
        ResponseComprobanteDto result = comprobanteService.findByNroComprobante(nroComprobante);
        return ResponseEntity.ok(result);
    }

    //mostrar comprobantes segun movimiento asignado
    @GetMapping("/mostrar")
    public ResponseEntity<ResponseSetComprobantesDto> viewComprobante() {
        ResponseSetComprobanteDto result = comprobanteService.findAll();
        return ResponseEntity.ok(result);
    }

    // crear comprobante
    @PostMapping("/guardar")
    public ResponseEntity<ResponseComprobanteDto> saveComprobante(@Valid @RequestBody RequestComprobanteDto requestComprobanteDto) {
        ResponseComprobanteDto result = comprobanteService.saveComprobante(requestComprobanteDto);
        return ResponseEntity.ok(result);
    }
    
    @PostMapping("/cambiar-estado/{id}")
    public ResponseEntity<ResponseComprobanteDto> updateState(@Valid @PathVariable String id ) {
        ResponseComprobanteDto result = comprobanteService.updateIsValid(id);
        return ResponseEntity.ok(result);
    }
    
    // asignar un comprobante a un movimiento
    @PostMapping("/asignar-comprobante")
    public ResponseEntity<ResponseAsignComprobanteDto> setMovimiento(@Valid @RequestBody RequestAsignComprobanteDto requestAsignComprobanteDto) {
        ResponseAsignComprobanteDto result = comprobanteService.asignMovimiento(requestAsignComprobanteDto);
        return ResponseEntity.ok(result);
    }
}
