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
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
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

    @ManyToMany( cascade = CascadeType.ALL )
    @JoinTable( name = "comprobante_movimiento",
        joinColumns = { @JoinColumn(name = "comprobante_id")},
        inverseJoinColumns = { @JoinColumn(name = "movimiento_id")}
    )
    private Set<Movimiento> movimientos = new HashSet<>();

    
    public Comprobante() {
    }


    
    public Comprobante(Long id, String tipoComprobante, String descripcion, Long nroComprobante,
            LocalDate fechaAltaComprobante, LocalDate fechaBajaComprobante) {
        this.id = id;
        this.tipoComprobante = tipoComprobante;
        this.descripcion = descripcion;
        this.nroComprobante = nroComprobante;
        this.fechaAltaComprobante = fechaAltaComprobante;
        this.fechaBajaComprobante = fechaBajaComprobante;
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
    public Set<Movimiento> getMovimientos() {
        return movimientos;
    }
    public void setMovimientos(Set<Movimiento> movimientos) {
        this.movimientos = movimientos;
    }

    public void addMovimiento(Movimiento movimiento){
        movimientos.add(movimiento);
        movimiento.getComprobantes().add(this);
    }

    public void removeMovimiento(Movimiento movimiento){
        movimientos.remove(movimiento);
        movimiento.getComprobantes().remove(this);
    }
    

    @Override
    public String toString() {
        return "{id=" + id +
         ", tipoComprobante=" + tipoComprobante +
          ", descripcion=" + descripcion + 
          ", nroComprobante=" + nroComprobante + 
          ", fechaAltaComprobante=" + fechaAltaComprobante + 
          ", fechaBajaComprobante=" + fechaBajaComprobante + 
          "}";
    }


    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((tipoComprobante == null) ? 0 : tipoComprobante.hashCode());
        result = prime * result + ((descripcion == null) ? 0 : descripcion.hashCode());
        result = prime * result + ((nroComprobante == null) ? 0 : nroComprobante.hashCode());
        result = prime * result + ((fechaAltaComprobante == null) ? 0 : fechaAltaComprobante.hashCode());
        result = prime * result + ((fechaBajaComprobante == null) ? 0 : fechaBajaComprobante.hashCode());
        result = prime * result + ((movimientos == null) ? 0 : movimientos.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Comprobante other = (Comprobante) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (tipoComprobante == null) {
            if (other.tipoComprobante != null)
                return false;
        } else if (!tipoComprobante.equals(other.tipoComprobante))
            return false;
        if (descripcion == null) {
            if (other.descripcion != null)
                return false;
        } else if (!descripcion.equals(other.descripcion))
            return false;
        if (nroComprobante == null) {
            if (other.nroComprobante != null)
                return false;
        } else if (!nroComprobante.equals(other.nroComprobante))
            return false;
        if (fechaAltaComprobante == null) {
            if (other.fechaAltaComprobante != null)
                return false;
        } else if (!fechaAltaComprobante.equals(other.fechaAltaComprobante))
            return false;
        if (fechaBajaComprobante == null) {
            if (other.fechaBajaComprobante != null)
                return false;
        } else if (!fechaBajaComprobante.equals(other.fechaBajaComprobante))
            return false;
        if (movimientos == null) {
            if (other.movimientos != null)
                return false;
        } else if (!movimientos.equals(other.movimientos))
            return false;
        return true;
    }



    

}
