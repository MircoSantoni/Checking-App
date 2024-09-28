package com.cuentacorrienteapp.cuentacorrienteapp.services.implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cuentacorrienteapp.cuentacorrienteapp.dtos.cuenta.*;
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
        Optional<Cuenta> optionalCuenta = cuentaRepository.findByName(requestCuentaDto.name());

        if (optionalCuenta.isPresent()) {
            Cuenta cuenta = optionalCuenta.get();
            return cuentaMapper.cuentaToResponseCuentaDto(cuenta);
        } else {
            Cuenta cuenta = cuentaMapper.requestCuentaDtoToCuenta(requestCuentaDto);
            return cuentaMapper.cuentaToResponseCuentaDto(cuentaRepository.save(cuenta));
        }
    }

    // @Override
    // @Transactional
    // public ResponseCuentaDto updateIsValid(RequestCuentaDto requestCuentaDto) {
    //     Optional<Cuenta> optionalCuenta = cuentaRepository.findById(requestCuentaDto).orElseThrow(() -> new EntityNotFoundException("Esta cuenta no existe"));
        

        
    //     return cuentaMapper.cuentaToResponseCuentaDto(cuenta);
    // }

}
