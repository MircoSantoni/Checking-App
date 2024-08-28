package com.cuentacorrienteapp.cuentacorrienteapp.services;

import java.util.List;
import java.util.Optional;

import com.cuentacorrienteapp.cuentacorrienteapp.entities.Cuenta;

public interface CuentaService {

    List<Cuenta> findAll();

    Optional<Cuenta> findById(Long id);

    Cuenta save(Cuenta cuenta);

    Optional<Cuenta> update( Long id ,Cuenta cuenta);

    Optional<Cuenta> delete(Long id);
}