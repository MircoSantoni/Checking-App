package com.cuentacorrienteapp.cuentacorrienteapp.dtos.cuenta;

import java.time.LocalDateTime;

import lombok.Builder;

@Builder
public record ResponseUpdateIsValidDto(
    String id,
    Long saldo,
    String name,
    LocalDateTime fechaBajaLogicaCuenta
    ) { }
