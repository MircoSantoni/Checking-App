package com.cuentacorrienteapp.cuentacorrienteapp.entities;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name="comprobantes")
public class Comprobante {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name="tipo_comprobante")
    private String tipoComprobante;

    @NotNull
    private String descripcion;
    
    @Min(value=0)
    @Column(name="nro_comprobante")
    private Long nroComprobante;    

    @Column(name="fecha_alta_comprobante")
    private LocalDate fechaAltaComprobante;

    @Column(name="fecha_baja_comprobante")
    private LocalDate fechaBajaComprobante;
    //orphanRemoval = true ,
    @OneToMany( cascade = CascadeType.ALL ,  mappedBy = "comprobante")
    private Set<ComprobanteMovimiento> comprobanteMovimientos;

    
    public Comprobante() {
        comprobanteMovimientos= new HashSet<>();
    }

    public Comprobante(Long id, String tipoComprobante, String descripcion, Long nroComprobante) {
        this.id = id;
        this.tipoComprobante = tipoComprobante;
        this.descripcion = descripcion;
        this.nroComprobante = nroComprobante;
    }
    
    public Comprobante(Long id, String tipoComprobante, String descripcion, Long nroComprobante,
            LocalDate fechaAltaComprobante, LocalDate fechaBajaComprobante, ComprobanteMovimiento comprobanteMovimiento) {
        this.id = id;
        this.tipoComprobante = tipoComprobante;
        this.descripcion = descripcion;
        this.nroComprobante = nroComprobante;
        this.fechaAltaComprobante = fechaAltaComprobante;
        this.fechaBajaComprobante = fechaBajaComprobante;
        this.comprobanteMovimientos = comprobanteMovimientos;
    }

    


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTipoComprobante() {
        return tipoComprobante;
    }

    public void setTipoComprobante(String tipoComprobante) {
        this.tipoComprobante = tipoComprobante;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Long getNroComprobante() {
        return nroComprobante;
    }

    public void setNroComprobante(Long nroComprobante) {
        this.nroComprobante = nroComprobante;
    }

    public LocalDate getFechaAltaComprobante() {
        return fechaAltaComprobante;
    }

    public void setFechaAltaComprobante(LocalDate fechaAltaComprobante) {
        this.fechaAltaComprobante = fechaAltaComprobante;
    }

    public LocalDate getFechaBajaComprobante() {
        return fechaBajaComprobante;
    }

    public void setFechaBajaComprobante(LocalDate fechaBajaComprobante) {
        this.fechaBajaComprobante = fechaBajaComprobante;
    }
    
    public Set<ComprobanteMovimiento> getComprobanteMovimientos() {
        return comprobanteMovimientos;
    }

    public void setComprobanteMovimientos(Set<ComprobanteMovimiento> comprobanteMovimientos) {
        this.comprobanteMovimientos = comprobanteMovimientos;
    }
    


    @Override
    public String toString() {
        return "{id=" + id +
         ", tipoComprobante=" + tipoComprobante +
          ", descripcion=" + descripcion + 
          ", nroComprobante=" + nroComprobante + 
          ", fechaAltaComprobante=" + fechaAltaComprobante + 
          ", fechaBajaComprobante=" + fechaBajaComprobante + 
          " comprobante Movimiento=" + comprobanteMovimientos +
          "}";
    }


    

}
