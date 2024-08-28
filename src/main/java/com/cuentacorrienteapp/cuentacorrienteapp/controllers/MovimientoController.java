package com.cuentacorrienteapp.cuentacorrienteapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cuentacorrienteapp.cuentacorrienteapp.entities.Movimiento;
import com.cuentacorrienteapp.cuentacorrienteapp.services.MovimientoService;

import jakarta.validation.Valid;

import java.util.List;
import java.util.Optional;


import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;




@RestController
@RequestMapping("/api/movimientos")
public class MovimientoController {

    @Autowired
    private MovimientoService service;

    @GetMapping
    public List<Movimiento> List(){
        return service.findAll();
    }
    

    @GetMapping("/{id}")
    public ResponseEntity<?> view(@PathVariable Long id) {
        Optional<Movimiento> optionalMovimiento = service.findById(id);

        if(optionalMovimiento.isPresent()){
            return ResponseEntity.ok().body(optionalMovimiento.orElseThrow());
        }
        return ResponseEntity.notFound().build();

    }
    
    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody Movimiento movimiento) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(movimiento));
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody Movimiento movimiento , @PathVariable Long id ) {
        Optional<Movimiento> optionalMovimiento = service.update(id, movimiento);

        if( optionalMovimiento.isPresent()){
            return ResponseEntity.ok(optionalMovimiento.orElseThrow());
        }
        
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Optional<Movimiento> optionalMovimiento = service.findById(id);

        if( optionalMovimiento.isPresent()) {
            service.delete(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
