package com.cuentacorrienteapp.cuentacorrienteapp.services;

import java.util.List;
import java.util.Optional;

import com.cuentacorrienteapp.cuentacorrienteapp.entities.Comprobante;

public interface ComprobanteService {

    List<Comprobante> findAll();

    Optional<Comprobante> findById(Long id);

    Comprobante save (Comprobante comprobante);

    Optional<Comprobante> update (Long id, Comprobante comprobante);

    Optional<Comprobante> delete (Long id);



}
