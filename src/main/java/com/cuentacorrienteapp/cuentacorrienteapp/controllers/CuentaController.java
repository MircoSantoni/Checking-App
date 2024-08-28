package com.cuentacorrienteapp.cuentacorrienteapp.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cuentacorrienteapp.cuentacorrienteapp.entities.Cuenta;
import com.cuentacorrienteapp.cuentacorrienteapp.services.CuentaService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;




@RestController
@RequestMapping("/api/cuentas")
public class CuentaController {

    @Autowired
    private CuentaService service;

    @GetMapping
    public List<Cuenta> list(){
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> view(@PathVariable Long id) {
        Optional<Cuenta> cuentaOptional = service.findById(id);

        if (cuentaOptional.isPresent()) {
            return ResponseEntity.ok().body(cuentaOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }
    
    @PostMapping
    public ResponseEntity<?> create(@RequestBody Cuenta cuenta) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(cuenta));
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody Cuenta cuenta, @PathVariable Long id) {
        Optional<Cuenta> cuentaOptional = service.update(id,cuenta);
        
        if (cuentaOptional.isPresent()){
            return ResponseEntity.ok(cuentaOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        Optional<Cuenta> cuentaOptional = service.delete(id);

        if (cuentaOptional.isPresent()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    
    
}
