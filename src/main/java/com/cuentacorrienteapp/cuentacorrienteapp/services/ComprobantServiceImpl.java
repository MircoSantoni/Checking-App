package com.cuentacorrienteapp.cuentacorrienteapp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import com.cuentacorrienteapp.cuentacorrienteapp.entities.Comprobante;
import com.cuentacorrienteapp.cuentacorrienteapp.repositories.ComprobanteRepository;

@Service
public class ComprobantServiceImpl implements ComprobanteService{

    @Autowired
    private ComprobanteRepository repository;

    @Override
    @Transactional( readOnly = true)
    public List<Comprobante> findAll() {
        return (List<Comprobante>)  repository.findAll();

    }

    @Override
    @Transactional( readOnly = true)
    public Optional<Comprobante> findById(Long id) {
        return repository.findById(id);
    }

    @Override   
    public Comprobante save(Comprobante comprobante) {
        return repository.save(comprobante);
    }

    @Override
    public Optional<Comprobante> update(Long id, Comprobante comprobante) {
        Optional<Comprobante> optionalComprobante = repository.findById(id);

        if(optionalComprobante.isPresent()){
            Comprobante compr = optionalComprobante.orElseThrow();
            compr.setTipoComprobante(comprobante.getTipoComprobante());
            compr.setDescripcion(comprobante.getDescripcion());
            compr.setNroComprobante(comprobante.getNroComprobante());
            compr.setComprobanteMovimientos(comprobante.getComprobanteMovimientos());
            
            return Optional.of(repository.save(compr));
        }
        return optionalComprobante;
    }

    @Override
    public Optional<Comprobante> delete(Long id) {
        Optional<Comprobante> optionalComprobante = repository.findById(id);

        optionalComprobante.ifPresent(compr -> {
            repository.delete(compr);
        });
        return optionalComprobante;
    }

}
