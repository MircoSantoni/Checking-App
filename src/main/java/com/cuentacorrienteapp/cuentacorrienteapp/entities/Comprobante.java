package com.cuentacorrienteapp.cuentacorrienteapp.entities;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@Data
@Entity
@Table(name="comprobantes")
public class Comprobante {

    @Id
    @GeneratedValue ( strategy = GenerationType.UUID)
    private String id;

    @Column(name="tipo_comprobante")
    private String tipoComprobante;

    private String descripcion;
    
    @Column(name="nro_comprobante")
    private Long nroComprobante;

    @Column(name="fecha_alta_comprobante")
    private LocalDateTime fechaAltaComprobante;

    @Column(name="fecha_baja_comprobante")
    private LocalDateTime fechaBajaComprobante;

    @ManyToMany( cascade = CascadeType.ALL )
    @JoinTable( name = "comprobante_movimiento",
        joinColumns = { @JoinColumn(name = "comprobante_id")},
        inverseJoinColumns = { @JoinColumn(name = "movimiento_id")}
    )
    private Set<Movimiento> movimientos = new HashSet<>();    

    private boolean isValid;

    @PrePersist
    public void onCreate() {
        this.fechaAltaComprobante = LocalDateTime.now();
    }

}
}
