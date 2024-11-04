package com.cuentacorrienteapp.cuentacorrienteapp.services.implementation;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;



import com.cuentacorrienteapp.cuentacorrienteapp.dtos.comprobante.*;
import com.cuentacorrienteapp.cuentacorrienteapp.entities.*;
import com.cuentacorrienteapp.cuentacorrienteapp.mappers.ComprobanteMapper;
import com.cuentacorrienteapp.cuentacorrienteapp.repositories.ComprobanteRepository;
import com.cuentacorrienteapp.cuentacorrienteapp.repositories.MovimientoRepository;
import com.cuentacorrienteapp.cuentacorrienteapp.services.ComprobanteService;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ComprobantServiceImpl implements ComprobanteService{

    private final ComprobanteRepository comprobanteRepository;
    private final MovimientoRepository movimientoRepository;
    
    private final ComprobanteMapper comprobanteMapper;

    @Override
    @Transactional( readOnly = true)
    public ResponseComprobanteDto findById(String id) {
        Comprobante comprobante = comprobanteRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Comprobante no encontrado"));
        return comprobanteMapper.comprobanteToResponseComprobanteDto(comprobante);
    }

    @Override
    @Transactional(readOnly = true)
    public Set<ResponseComprobanteDto> findByNroComprobante(Long nroComprobante) {
        Optional<List<Comprobante>> comprobantesOpt = comprobanteRepository.findByNroComprobante(nroComprobante);
        
        if (comprobantesOpt.isEmpty() ) {
            throw new EntityNotFoundException("No se encontraron comprobantes con el número " + nroComprobante);
        }
        return comprobantesOpt.get().stream()
            .map(comprobanteMapper::comprobanteToResponseComprobanteDto)
            .collect(Collectors.toSet());
    }

    @Override
    @Transactional(readOnly = true)
    public Set<ResponseSetComprobanteDto> findAll() {
        try {
            return comprobanteRepository.findAll().stream()
                .<ResponseSetComprobanteDto>map(comprobante -> ResponseSetComprobanteDto.builder()
                    .id(comprobante.getId())
                    .tipo_comprobante(comprobante.getTipoComprobante())
                    .descripcion(comprobante.getDescripcion())
                    .nroComprobante(comprobante.getNroComprobante())
                    .fechaAltaComprobante(comprobante.getFechaAltaComprobante())
                    .movimiento(comprobante.getMovimientos())
                    .build())
                .collect(Collectors.toSet());
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener los comprobantes", e);
        }
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


    @Override
    public ResponseAsignComprobanteDto asignMovimiento (RequestAsignComprobanteDto requestAsignComprobanteDto) {
        Comprobante comprobante = comprobanteRepository.findById(requestAsignComprobanteDto.idComprobante()).orElseThrow(() -> new EntityNotFoundException("Comprobante no encontrado"));

        Movimiento movimiento = movimientoRepository.findById(requestAsignComprobanteDto.idMovimiento()).orElseThrow(() -> new EntityNotFoundException("Movimiento no encontrado"));

        
        try {
            comprobante.addMovimiento(movimiento);
            comprobanteRepository.save(comprobante);
            return comprobanteMapper.comprobanteToResponseAsignComprobanteDto(comprobante);
        } catch (Exception ex) {
            throw new RuntimeException("Error general al asignar movimiento");
        }
    }
}
