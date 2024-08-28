package com.cuentacorrienteapp.cuentacorrienteapp.repositories;

import org.springframework.data.repository.CrudRepository;

import com.cuentacorrienteapp.cuentacorrienteapp.entities.Movimiento;


public interface MovimientoRepository extends CrudRepository<Movimiento, Long>{
} 