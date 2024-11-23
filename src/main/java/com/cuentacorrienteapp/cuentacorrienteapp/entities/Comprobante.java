package com.cuentacorrienteapp.cuentacorrienteapp.entities;

import java.time.*;

import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@Entity
@Table(name="comprobantes")
public class Comprobante {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    
    @Column(name="tipo_comprobante")
    private String tipoComprobante;
    
    private String descripcion;
    
    private Double montoComprobante;

    @Column(name="nro_comprobante")
    private Long nroComprobante;
    
    @Column(name="fecha_comprobante")
    private LocalDate fechaComprobante;
    
    @Column(name="fecha_alta_comprobante")
    private LocalDateTime fechaAltaComprobante;
    
    @Column(name="fecha_baja_comprobante")
    private LocalDateTime fechaBajaComprobante;
    
    @ManyToOne
    @JoinColumn(name = "movimiento_id")
    private Movimiento movimiento;
    
    private boolean isValid;
    
    @PrePersist
    public void onCreate() {
        ZoneId zoneId = ZoneId.of("America/Argentina/Buenos_Aires");
        this.fechaAltaComprobante = ZonedDateTime.now(zoneId).toLocalDateTime();
    }
}