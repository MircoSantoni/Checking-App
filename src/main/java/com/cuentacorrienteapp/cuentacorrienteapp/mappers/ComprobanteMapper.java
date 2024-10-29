package com.cuentacorrienteapp.cuentacorrienteapp.mappers;

import com.cuentacorrienteapp.cuentacorrienteapp.dtos.comprobante.*;
import com.cuentacorrienteapp.cuentacorrienteapp.entities.Comprobante;

import org.mapstruct.Mapper;

@Mapper( componentModel = "spring")
public interface ComprobanteMapper {
    
    Comprobante responseComprobanteDtoToComprobante(ResponseComprobanteDto responseComprobanteDto);
    ResponseComprobanteDto comprobanteToResponseComprobanteDto(Comprobante comprobante);

    Comprobante requestComprobanteDtoToComprobante(RequestComprobanteDto requestComprobanteDto);
    RequestComprobanteDto comprobanteToRequestComprobanteDto(Comprobante comprobante);

   Comprobante requestAsignComprobanteDtoToComprobante(RequestAsignComprobanteDto requestAsignComprobanteDto);
   ResponseAsignComprobanteDto comprobanteToResponseAsignComprobanteDto(Comprobante comprobante);

   ResponseSetComprobanteDto comprobanteToResponseSetComprobanteDto (Comprobante comprobante);
}