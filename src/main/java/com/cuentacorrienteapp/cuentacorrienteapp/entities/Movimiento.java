package com.cuentacorrienteapp.cuentacorrienteapp.entities;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table
public class Movimiento {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="importe_movimiento")
    private Long importeMovimiento;

    @Column(name="medio_pago")
    private String medioPago;

    @Column(name="comentario_movimiento")
    private String comentarioMovimiento;

    @Column(name="fecha_alta_movimiento")
    private LocalDate fechaAltaMovimiento;

    @Column(name="fecha_baja_movimiento")
    private LocalDate fechaBajaMovimiento;


}
