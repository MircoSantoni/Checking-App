    package com.cuentacorrienteapp.cuentacorrienteapp.services.implementation;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cuentacorrienteapp.cuentacorrienteapp.dtos.cuenta.*;
import com.cuentacorrienteapp.cuentacorrienteapp.dtos.cuenta.ResponseUpdateIsValidDto;

import com.cuentacorrienteapp.cuentacorrienteapp.entities.Cuenta;
import com.cuentacorrienteapp.cuentacorrienteapp.mappers.CuentaMapper;
import com.cuentacorrienteapp.cuentacorrienteapp.repositories.CuentaRepository;
import com.cuentacorrienteapp.cuentacorrienteapp.services.CuentaService;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CuentaServiceImpl implements CuentaService{


    private final CuentaRepository cuentaRepository;
    private final CuentaMapper cuentaMapper;

    @Override
    @Transactional( readOnly = true)
    public List<ResponseCuentaDto> findAll() {
        List<Cuenta> cuentaList = cuentaRepository.findAll();
        return cuentaMapper.listCuentaToListResponseCuentaDto(cuentaList);
    }

    @Override
    @Transactional( readOnly = true)
    public ResponseCuentaDto findById(String id) {
        Cuenta cuenta = cuentaRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Esta cuenta no existe"));
        return cuentaMapper.cuentaToResponseCuentaDto(cuenta);
    }

    @Override
    @Transactional 
    public ResponseCuentaDto save(RequestCuentaDto requestCuentaDto) {
        if (requestCuentaDto == null) {
            throw new IllegalArgumentException("El request no puede ser null");
        }

        Cuenta nuevaCuenta = cuentaMapper.requestCuentaDtoToCuenta(requestCuentaDto);
        nuevaCuenta.setIsValid(true);
        cuentaRepository.save(nuevaCuenta);

        return cuentaMapper.cuentaToResponseCuentaDto(nuevaCuenta);
    }

    @Override
    @Transactional
    public ResponseUpdateIsValidDto updateIsValid(String id) {
        Cuenta cuenta = cuentaRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Esta cuenta no existe"));
    
        cuenta.setIsValid(!cuenta.isValid());

        if (!cuenta.isValid()) {
            ZoneId zoneId = ZoneId.of("America/Argentina/Buenos_Aires");
            cuenta.setFechaBajaLogicaCuenta(ZonedDateTime.now(zoneId).toLocalDateTime());
        } else {
            cuenta.setFechaBajaLogicaCuenta(null);
        }
    
        cuentaRepository.save(cuenta);

        return cuentaMapper.cuentaToResponseUpdateIsValidDto(cuenta);
    }

}
