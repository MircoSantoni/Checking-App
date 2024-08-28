package com.cuentacorrienteapp.cuentacorrienteapp.repositories;

import org.springframework.data.repository.CrudRepository;

import com.cuentacorrienteapp.cuentacorrienteapp.entities.Cuenta;

public interface CuentaRepository extends CrudRepository<Cuenta , Long>{

    
}