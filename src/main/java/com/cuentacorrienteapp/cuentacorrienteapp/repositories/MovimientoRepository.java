package com.cuentacorrienteapp.cuentacorrienteapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cuentacorrienteapp.cuentacorrienteapp.entities.Movimiento;

public interface MovimientoRepository extends JpaRepository<Movimiento, String>{
} 