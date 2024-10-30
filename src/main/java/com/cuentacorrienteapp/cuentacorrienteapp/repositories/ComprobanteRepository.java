package com.cuentacorrienteapp.cuentacorrienteapp.repositories;

import java.util.Optional;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cuentacorrienteapp.cuentacorrienteapp.entities.Comprobante;

public interface ComprobanteRepository extends JpaRepository<Comprobante, String>{
    Optional<List<Comprobante>> findByNroComprobante(Long nroComprobante);
} 