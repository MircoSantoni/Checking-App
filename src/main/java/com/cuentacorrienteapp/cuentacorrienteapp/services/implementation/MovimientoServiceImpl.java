package com.cuentacorrienteapp.cuentacorrienteapp.services.implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cuentacorrienteapp.cuentacorrienteapp.entities.Movimiento;
import com.cuentacorrienteapp.cuentacorrienteapp.repositories.MovimientoRepository;
import com.cuentacorrienteapp.cuentacorrienteapp.services.MovimientoService;

@Service
public class MovimientoServiceImpl implements MovimientoService{

    @Autowired
    private MovimientoRepository repository;

    @Override
    @Transactional(readOnly = true)
    public List<Movimiento> findAll() {
        return (List<Movimiento>) repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Movimiento> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    @Transactional
    public Movimiento save(Movimiento movimiento) {
        return repository.save(movimiento);
    }

    @Override
    @Transactional
    public Optional<Movimiento> update(Long id, Movimiento movimiento) {
        Optional<Movimiento> optionalMovimiento = repository.findById(id);

        if (optionalMovimiento.isPresent()) {
            Movimiento mov = optionalMovimiento.orElseThrow();
            mov.setImporteMovimiento(movimiento.getImporteMovimiento());
            mov.setMedioPago(movimiento.getMedioPago());
            mov.setComentarioMovimiento(movimiento.getComentarioMovimiento());
            mov.setFechaAltaMovimiento(movimiento.getFechaAltaMovimiento());
            mov.setFechaBajaMovimiento(movimiento.getFechaBajaMovimiento());

            return Optional.of(repository.save(mov));
        }
        return optionalMovimiento;
    }

    @Override
    @Transactional
    public Optional<Movimiento> delete(Long id) {
        Optional<Movimiento> optionalMovimiento = repository.findById(id);
        
        optionalMovimiento.ifPresent( mov -> {
            repository.delete(mov);
        });
        return optionalMovimiento;
    }

}
