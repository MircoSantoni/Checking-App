package com.cuentacorrienteapp.cuentacorrienteapp.services.implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cuentacorrienteapp.cuentacorrienteapp.entities.Cuenta;
import com.cuentacorrienteapp.cuentacorrienteapp.repositories.CuentaRepository;
import com.cuentacorrienteapp.cuentacorrienteapp.services.CuentaService;

@Service
public class CuentaServiceImpl implements CuentaService{

    @Autowired
    private CuentaRepository repository;

    @Override
    @Transactional( readOnly = true)
    public List<Cuenta> findAll() {
        return (List<Cuenta>) repository.findAll();
    }

    @Override
    @Transactional( readOnly = true)
    public Optional<Cuenta> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    @Transactional
    public Cuenta save(Cuenta cuenta) {
        return repository.save(cuenta);
    }

    @Override
    @Transactional
    public Optional<Cuenta> update(Long id, Cuenta cuenta) {
        Optional<Cuenta> optionalCuenta = repository.findById(id);

        if (optionalCuenta.isPresent()) {
            Cuenta cuen = optionalCuenta.orElseThrow();
            cuen.setName(cuenta.getName());
            cuen.setSaldo(cuenta.getSaldo());

            return Optional.of(repository.save(cuen));
        }
        return optionalCuenta;
    }

    @Override
    public Optional<Cuenta> delete(Long id) {
        Optional<Cuenta> optionalCuenta = repository.findById(id);

        optionalCuenta.ifPresent(compr -> {
            repository.delete(compr);
        });
        return optionalCuenta;
    }

}
