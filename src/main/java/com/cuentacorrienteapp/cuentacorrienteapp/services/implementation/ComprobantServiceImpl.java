package com.cuentacorrienteapp.cuentacorrienteapp.services.implementation;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import com.cuentacorrienteapp.cuentacorrienteapp.dtos.comprobante.RequestComprobanteDto;
import com.cuentacorrienteapp.cuentacorrienteapp.dtos.comprobante.ResponseComprobanteDto;
import com.cuentacorrienteapp.cuentacorrienteapp.entities.Comprobante;
import com.cuentacorrienteapp.cuentacorrienteapp.exceptions.ResourceAlreadyExistsException;
import com.cuentacorrienteapp.cuentacorrienteapp.mappers.ComprobanteMapper;
import com.cuentacorrienteapp.cuentacorrienteapp.repositories.ComprobanteRepository;
import com.cuentacorrienteapp.cuentacorrienteapp.services.ComprobanteService;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ComprobantServiceImpl implements ComprobanteService{

    private final ComprobanteRepository comprobanteRepository;
    
    private final ComprobanteMapper comprobanteMapper;

    @Override
    @Transactional( readOnly = true)
    public ResponseComprobanteDto findById(String id) {
        Comprobante comprobante = comprobanteRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Comprobante no encontrado"));
        return comprobanteMapper.comprobanteToResponseComprobanteDto(comprobante);
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseComprobanteDto findByNroComprobante(Long nroComprobante) {
        Comprobante comprobante = comprobanteRepository.findByNroComprobante(nroComprobante).orElseThrow(() -> new EntityNotFoundException("Comprobante no encontrado"));
        return comprobanteMapper.comprobanteToResponseComprobanteDto(comprobante);
    }

@Override
public ResponseComprobanteDto saveComprobante(RequestComprobanteDto requestComprobanteDto) {
    try {
        Comprobante newComprobante = comprobanteMapper.requestComprobanteDtoToComprobante(requestComprobanteDto);
        Comprobante savedComprobante = comprobanteRepository.save(newComprobante);
        return comprobanteMapper.comprobanteToResponseComprobanteDto(savedComprobante);
    } catch (Exception e) {
        throw new RuntimeException("Error general en el guardado del comprobante");
    }
    }
    
    @Override
    public ResponseComprobanteDto updateIsValid(String id) {
        Comprobante comprobante = comprobanteRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Comprobante no encontrado"));

        comprobante.setValid(!comprobante.isValid());
        comprobanteRepository.save(comprobante);
        return comprobanteMapper.comprobanteToResponseComprobanteDto(comprobante);
    }

}
