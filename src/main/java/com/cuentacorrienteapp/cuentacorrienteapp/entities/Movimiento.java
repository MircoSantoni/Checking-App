package com.cuentacorrienteapp.cuentacorrienteapp.entities;

import java.time.LocalDateTime;
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
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.RequiredArgsConstructor;


@Data
@RequiredArgsConstructor
@Entity
@Table(name="movimientos")
public class Movimiento {

    @Id
    @GeneratedValue ( strategy = GenerationType.UUID)
    private String id;

    @Column(name="importe_movimiento")
    private Long importeMovimiento;

    @Column(name="medio_pago")
    private String medioPago;

    @NotEmpty
    @Column(name="comentario_movimiento")
    private String comentarioMovimiento;

    @Column(name="fecha_alta_movimiento")
    private LocalDateTime fechaAltaMovimiento;

    @Column(name="fecha_baja_movimiento")
    private LocalDateTime fechaBajaMovimiento;

    @ManyToMany(  mappedBy = "movimientos")
    private Set<Comprobante> comprobantes = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "cuenta_id")
    private Cuenta cuenta;
    
    @PrePersist
    protected void onCreate() {
        this.fechaAltaMovimiento = LocalDateTime.now();
    }
}