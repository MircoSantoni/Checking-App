package com.cuentacorrienteapp.cuentacorrienteapp.dtos.cuenta;

public record RequestCuentaDto(

    String name,
    String nombreProveedor,
    String numeroCelular,
    String emailProveedor,
    String direccionProveedor
) {}