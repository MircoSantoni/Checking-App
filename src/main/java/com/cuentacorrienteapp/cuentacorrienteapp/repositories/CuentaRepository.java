package com.cuentacorrienteapp.cuentacorrienteapp.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cuentacorrienteapp.cuentacorrienteapp.entities.Cuenta;

public interface CuentaRepository extends JpaRepository<Cuenta, String>{

    Optional<Cuenta> findByName(String name);
    
}