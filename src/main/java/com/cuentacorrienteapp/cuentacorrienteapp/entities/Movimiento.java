package com.cuentacorrienteapp.cuentacorrienteapp.entities;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@EqualsAndHashCode
@RequiredArgsConstructor
@Entity
@Table(name="movimientos")
public class Movimiento {

    @Id
    @GeneratedValue ( strategy = GenerationType.UUID)
    private String id;

    @Min(value=0)
    @Column(name="importe_movimiento")
    private Long importeMovimiento;

    @NotNull
    @Column(name="medio_pago")
    private String medioPago;

    @NotEmpty
    @Column(name="comentario_movimiento")
    private String comentarioMovimiento;

    @Column(name="fecha_alta_movimiento")
    private LocalDate fechaAltaMovimiento;

    @Column(name="fecha_baja_movimiento")
    private LocalDate fechaBajaMovimiento;

    @ManyToMany(  mappedBy = "movimientos")
    private Set<Comprobante> comprobantes = new HashSet<>();

    @ManyToOne
    @JoinColumn(name="cuenta_id")
    private Cuenta cuenta;



}