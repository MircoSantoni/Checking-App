package com.cuentacorrienteapp.cuentacorrienteapp.mappers;

import com.cuentacorrienteapp.cuentacorrienteapp.dtos.cuenta.*;
import com.cuentacorrienteapp.cuentacorrienteapp.entities.Cuenta;

import java.util.List;

import org.mapstruct.Mapper;

@Mapper(componentModel="spring")
public interface CuentaMapper {
    Cuenta responseCuentaDtoToCuenta (ResponseCuentaDto responseCuentaDto);
    ResponseCuentaDto cuentaToResponseCuentaDto(Cuenta cuenta);

    Cuenta requestCuentaDtoToCuenta (RequestCuentaDto requestCuentaDto);
    RequestCuentaDto cuentaToRequestCuentaDto (Cuenta cuenta);

    List<ResponseCuentaDto> listCuentaToListResponseCuentaDto(List<Cuenta> cuentaList);
    
}