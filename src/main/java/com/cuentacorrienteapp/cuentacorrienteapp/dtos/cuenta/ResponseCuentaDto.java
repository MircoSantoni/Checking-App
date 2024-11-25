package com.cuentacorrienteapp.cuentacorrienteapp.dtos.cuenta;

import java.time.LocalDateTime;
import java.util.Set;

import com.cuentacorrienteapp.cuentacorrienteapp.entities.Movimiento;

import lombok.Builder;

@Builder
public record ResponseCuentaDto(
    String id,
    Long saldo,
    String name,
    String nombreProveedor,
    String numeroCelular,
    String emailProveedor,
    String direccionProveedor,
    LocalDateTime fechaBajaLogicaCuenta,
    Set<Movimiento> movimiento
) {}