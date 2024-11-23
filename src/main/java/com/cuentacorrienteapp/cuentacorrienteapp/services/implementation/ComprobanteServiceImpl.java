package com.cuentacorrienteapp.cuentacorrienteapp.services.implementation;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZoneId;
import java.time.ZonedDateTime;
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
public class ComprobanteServiceImpl implements ComprobanteService {

    private final ComprobanteRepository comprobanteRepository;
    private final MovimientoRepository movimientoRepository;
    private final ComprobanteMapper comprobanteMapper;

    @Override
    @Transactional(readOnly = true)
    public ResponseComprobanteDto findById(String id) {
        Comprobante comprobante = comprobanteRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Comprobante no encontrado"));
        return comprobanteMapper.comprobanteToResponseComprobanteDto(comprobante);
    }

    @Override
    @Transactional(readOnly = true)
    public Set<ResponseComprobanteDto> findByNroComprobante(Long nroComprobante) {
        Optional<List<Comprobante>> comprobantesOpt = comprobanteRepository.findByNroComprobante(nroComprobante);
        
        if (comprobantesOpt.isEmpty()) {
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
                    .tipoComprobante(comprobante.getTipoComprobante())
                    .descripcion(comprobante.getDescripcion())
                    .montoComprobante(comprobante.getMontoComprobante())
                    .nroComprobante(comprobante.getNroComprobante())
                    .fechaComprobante(comprobante.getFechaComprobante())
                    .fechaAltaComprobante(comprobante.getFechaAltaComprobante())
                    .movimientoId(comprobante.getMovimiento() != null ? comprobante.getMovimiento().getId() : null)
                    .isValid(comprobante.isValid())
                    .build())
                .collect(Collectors.toSet());
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener los comprobantes", e);
        }
    }

    @Override
    @Transactional
    public ResponseComprobanteDto saveComprobante(RequestComprobanteDto requestComprobanteDto) {
        try {
            Comprobante newComprobante = comprobanteMapper.requestComprobanteDtoToComprobante(requestComprobanteDto);
            newComprobante.setValid(true);
            
            // Si se proporciona un movimiento, lo validamos y asignamos
            if (requestComprobanteDto.movimientoId() != null) {
                Movimiento movimiento = movimientoRepository.findById(requestComprobanteDto.movimientoId())
                    .orElseThrow(() -> new EntityNotFoundException("Movimiento no encontrado"));
                newComprobante.setMovimiento(movimiento);
            }
            
            Comprobante savedComprobante = comprobanteRepository.save(newComprobante);
            return comprobanteMapper.comprobanteToResponseComprobanteDto(savedComprobante);
        } catch (Exception e) {
            throw new RuntimeException("Error al guardar el comprobante", e);
        }
    }

    @Override
    @Transactional
    public ResponseComprobanteDto updateIsValid(String id) {
        Comprobante comprobante = comprobanteRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Comprobante no encontrado"));

        comprobante.setValid(!comprobante.isValid());
        if (!comprobante.isValid()) {
            ZoneId zoneId = ZoneId.of("America/Argentina/Buenos_Aires");
            comprobante.setFechaBajaComprobante(ZonedDateTime.now(zoneId).toLocalDateTime());
        } else {
            comprobante.setFechaBajaComprobante(null);
        }
        
        comprobanteRepository.save(comprobante);
        return comprobanteMapper.comprobanteToResponseComprobanteDto(comprobante);
    }

    @Override
    @Transactional
    public ResponseAsignMovimientoDto asignMovimiento(RequestAsignMovimientoDto requestAsignMovimientoDto) {
        Comprobante comprobante = comprobanteRepository.findById(requestAsignMovimientoDto.idComprobante())
            .orElseThrow(() -> new EntityNotFoundException("Comprobante no encontrado"));

        Movimiento movimiento = movimientoRepository.findById(requestAsignMovimientoDto.idMovimiento())
            .orElseThrow(() -> new EntityNotFoundException("Movimiento no encontrado"));

        try {
            comprobante.setMovimiento(movimiento);
            comprobanteRepository.save(comprobante);
            return comprobanteMapper.comprobanteToResponseAsignMovimientoDto(comprobante);
        } catch (Exception ex) {
            throw new RuntimeException("Error al asignar movimiento al comprobante");
        }
    }

}