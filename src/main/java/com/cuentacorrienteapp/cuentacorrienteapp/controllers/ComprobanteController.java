package com.cuentacorrienteapp.cuentacorrienteapp.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

    import com.cuentacorrienteapp.cuentacorrienteapp.entities.Comprobante;
import com.cuentacorrienteapp.cuentacorrienteapp.services.ComprobanteService;

import jakarta.validation.Valid;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;

@RestController
@RequestMapping("/api/comprobantes")
public class ComprobanteController {

    @Autowired
    private ComprobanteService service;

    @GetMapping
    public List<Comprobante> list() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> view(@PathVariable Long id) {
        Optional<Comprobante> optionalComprobante = service.findById(id);

        if (optionalComprobante.isPresent()) {
            return ResponseEntity.ok(optionalComprobante.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody Comprobante comprobante) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(comprobante));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody Comprobante comprobante, @PathVariable Long id) {
        Optional<Comprobante> optionalComprobante = service.update(id, comprobante);

        if (optionalComprobante.isPresent()) {
            return ResponseEntity.ok(optionalComprobante.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Optional<Comprobante> optionalComprobante = service.findById(id);

        if (optionalComprobante.isPresent()) {
            service.delete(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
