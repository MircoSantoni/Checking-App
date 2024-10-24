package com.cuentacorrienteapp.cuentacorrienteapp.mappers;

import org.mapstruct.Mapper;
import com.cuentacorrienteapp.cuentacorrienteapp.entities.Movimiento;

import com.cuentacorrienteapp.cuentacorrienteapp.dtos.movimiento.*;

@Mapper(componentModel="spring")
public interface MovimientoMapper {

    // Crear movimiento
    Movimiento requestCreateMovimientoDtoToMovimiento (RequestCreateMovimientoDto requestCreateMovimientoDto);
    ResponseCreateMovimientoDto movimientoToResponseCreateMovimientoDto (Movimiento movimiento);

    //Modificar movimiento

    Movimiento requestPutMovCuentaDtoToMovimiento(RequestPutMovCuentaDto requestPutMovCuentaDto);
    ResponsePutMovCuentaDto movimientoToResponsePutMovCuentaDto(Movimiento movimiento);
}
