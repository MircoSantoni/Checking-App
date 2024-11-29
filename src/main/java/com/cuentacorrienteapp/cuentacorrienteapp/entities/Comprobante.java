package com.cuentacorrienteapp.cuentacorrienteapp.entities;

import java.time.*;

import com.cuentacorrienteapp.cuentacorrienteapp.enums.TipoComprobante;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="comprobantes")
@Getter
@Setter
@ToString(exclude = "movimiento")
@EqualsAndHashCode(exclude = "movimiento")
public class Comprobante {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    
    @Column(name="tipo_comprobante")
    @Enumerated(EnumType.STRING)
    private TipoComprobante tipoComprobante;
    
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
    @JsonIgnore
    @JoinColumn(name = "movimiento_id")
    private Movimiento movimiento;
    
    private boolean isValid;
    
    @PrePersist
    public void onCreate() {
        ZoneId zoneId = ZoneId.of("America/Argentina/Buenos_Aires");
        this.fechaAltaComprobante = ZonedDateTime.now(zoneId).toLocalDateTime();
    }
}