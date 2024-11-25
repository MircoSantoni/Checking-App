package com.cuentacorrienteapp.cuentacorrienteapp.mappers;

import com.cuentacorrienteapp.cuentacorrienteapp.dtos.cuenta.*;
import com.cuentacorrienteapp.cuentacorrienteapp.entities.Cuenta;

import java.util.List;

import org.mapstruct.Mapper;

@Mapper(componentModel="spring")
public interface CuentaMapper {
    Cuenta responseCuentaDtoToCuenta (ResponseCuentaDto responseCuentaDto);

    RequestCuentaDto cuentaToRequestCuentaDto (Cuenta cuenta);

        // Mapea la entidad Cuenta a ResponseCuentaDto
        ResponseCuentaDto cuentaToResponseCuentaDto(Cuenta cuenta);
    
        // Mapea el DTO a la entidad Cuenta
        Cuenta requestCuentaDtoToCuenta(RequestCuentaDto requestCuentaDto);
         
        ResponseUpdateIsValidDto cuentaToResponseUpdateIsValidDto(Cuenta cuenta);

    List<ResponseCuentaDto> listCuentaToListResponseCuentaDto(List<Cuenta> cuentaList);

    Cuenta requestAddMovimientoDtoToCuenta (RequestAddMovimientoDto requestAddMovimientoDto);

    ResponseAddMovimientoDto cuentaToResponseAddMovimientoDto (Cuenta cuenta);
    
}