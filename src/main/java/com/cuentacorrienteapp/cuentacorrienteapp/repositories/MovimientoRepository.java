package com.cuentacorrienteapp.cuentacorrienteapp.repositories;

import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cuentacorrienteapp.cuentacorrienteapp.entities.Movimiento;

public interface MovimientoRepository extends JpaRepository<Movimiento, String>{

    @Query("SELECT m FROM Movimiento m WHERE m.fechaBajaMovimiento IS NULL")
    Set<Movimiento> findAllActive();
    
    Optional<Movimiento> findTopByOrderByNumeroMovimientoDesc();

} 