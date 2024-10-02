package com.cuentacorrienteapp.cuentacorrienteapp.services.implementation;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import com.cuentacorrienteapp.cuentacorrienteapp.dtos.comprobante.RequestComprobanteDto;
import com.cuentacorrienteapp.cuentacorrienteapp.dtos.comprobante.ResponseComprobanteDto;
import com.cuentacorrienteapp.cuentacorrienteapp.entities.Comprobante;
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
        Optional<Comprobante> optionalComprobante = comprobanteRepository.findByNroComprobante(requestComprobanteDto.nroComprobante());
    
        if (optionalComprobante.isPresent()) {
            // si el comprobante ya existe se devuelve
            Comprobante existingComprobante = optionalComprobante.get();
            return comprobanteMapper.comprobanteToResponseComprobanteDto(existingComprobante);
        } else {
            // si no existe se guarda
            Comprobante newComprobante = comprobanteMapper.requestComprobanteDtoToComprobante(requestComprobanteDto);
            return comprobanteMapper.comprobanteToResponseComprobanteDto(comprobanteRepository.save(newComprobante));
        }
    }   
    
    @Override
    public ResponseComprobanteDto updateIsValid(Long nroComprobante) {
        Comprobante comprobante = comprobanteRepository.findByNroComprobante(nroComprobante).orElseThrow(() -> new EntityNotFoundException("Comprobante no encontrado"));

        comprobante.setValid(!comprobante.isValid());
        comprobanteRepository.save(comprobante);
        return comprobanteMapper.comprobanteToResponseComprobanteDto(comprobante);
    }

}
