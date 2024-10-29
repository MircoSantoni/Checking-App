package com.cuentacorrienteapp.cuentacorrienteapp.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cuentacorrienteapp.cuentacorrienteapp.entities.Comprobante;

public interface ComprobanteRepository extends JpaRepository<Comprobante, String>{
    Optional<Comprobante> findByNroComprobante(Long nroComprobante);
    List<Comprobante> findAllByNroComprobante(Long nroComprobante);
} 