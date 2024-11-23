package com.cuentacorrienteapp.cuentacorrienteapp.entities;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@Entity
@Table(name="movimientos")
public class Movimiento {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
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
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cuenta_id", nullable = false)
    private Cuenta cuenta;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "movimiento")
    private Set<Comprobante> comprobantes = new HashSet<>();
    
    private Boolean isValid;
    
    @PrePersist
    protected void onCreate() {
        this.fechaAltaMovimiento = LocalDateTime.now();
    }

    // Método helper para agregar comprobantes
    public void addComprobante(Comprobante comprobante) {
        comprobantes.add(comprobante);
        comprobante.setMovimiento(this);
    }
    
}