package com.cuentacorrienteapp.cuentacorrienteapp.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cuentacorrienteapp.cuentacorrienteapp.entities.Cuenta;
@Repository
public interface CuentaRepository extends JpaRepository<Cuenta, String>{

    Optional<Cuenta> findByName(String name);
    
}