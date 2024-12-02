package com.cuentacorrienteapp.cuentacorrienteapp.entities;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import com.cuentacorrienteapp.cuentacorrienteapp.enums.MedioPago;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Entity
@Table(name="movimientos")
@Getter
@Setter
@ToString(exclude = {"cuenta", "comprobantes"})
@EqualsAndHashCode(exclude = {"cuenta", "comprobantes"})
public class Movimiento {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    
    @Column(name="importe_movimiento")
    private Long importeMovimiento;
    
    @Column(name="medio_pago")
    @Enumerated(EnumType.STRING)
    private MedioPago medioPago;
    
    @NotEmpty
    @Column(name="comentario_movimiento")
    private String comentarioMovimiento;
    
    @Column(name="fecha_alta_movimiento")
    private LocalDateTime fechaAltaMovimiento;
    
    @Column(name="fecha_baja_movimiento")
    private LocalDateTime fechaBajaMovimiento;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinColumn(name = "cuenta_id", nullable = false)
    private Cuenta cuenta;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "movimiento")
    private Set<Comprobante> comprobantes = new HashSet<>();
    
    private Boolean isValid;
    
    // @PrePersist
    // protected void onCreate() {
    //     this.fechaAltaMovimiento = LocalDateTime.now();
    // }

    public void addComprobante(Comprobante comprobante) {
        comprobantes.add(comprobante);
        comprobante.setMovimiento(this);
    }
}