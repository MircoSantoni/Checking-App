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

@Entity
@Table(name="movimientos")
public class Movimiento {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;

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

    public Movimiento() {
    }



    public Movimiento(Long id, Long importeMovimiento, String medioPago, String comentarioMovimiento,
            LocalDate fechaAltaMovimiento, LocalDate fechaBajaMovimiento) {
        this.id = id;
        this.importeMovimiento = importeMovimiento;
        this.medioPago = medioPago;
        this.comentarioMovimiento = comentarioMovimiento;
        this.fechaAltaMovimiento = fechaAltaMovimiento;
        this.fechaBajaMovimiento = fechaBajaMovimiento;
    }


    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getImporteMovimiento() {
        return importeMovimiento;
    }
    public void setImporteMovimiento(Long importeMovimiento) {
        this.importeMovimiento = importeMovimiento;
    }
    public String getMedioPago() {
        return medioPago;
    }
    public void setMedioPago(String medioPago) {
        this.medioPago = medioPago;
    }
    public String getComentarioMovimiento() {
        return comentarioMovimiento;
    }
    public void setComentarioMovimiento(String comentarioMovimiento) {
        this.comentarioMovimiento = comentarioMovimiento;
    }
    public LocalDate getFechaAltaMovimiento() {
        return fechaAltaMovimiento;
    }
    public void setFechaAltaMovimiento(LocalDate fechaAltaMovimiento) {
        this.fechaAltaMovimiento = fechaAltaMovimiento;
    }
    public LocalDate getFechaBajaMovimiento() {
        return fechaBajaMovimiento;
    }
    public void setFechaBajaMovimiento(LocalDate fechaBajaMovimiento) {
        this.fechaBajaMovimiento = fechaBajaMovimiento;
    }

    public Set<Comprobante> getComprobantes() {
        return comprobantes;
    }
    public void setComprobantes(Set<Comprobante> comprobantes) {
        this.comprobantes = comprobantes;
    }


    @Override
    public String toString() {
        return "{ id=" + id + 
        ", importeMovimiento=" + importeMovimiento + 
        ", medioPago=" + medioPago + 
        ", comentarioMovimiento=" + comentarioMovimiento + 
        ", fechaAltaMovimiento=" + fechaAltaMovimiento+ 
        ", fechaBajaMovimiento=" + fechaBajaMovimiento + 
        "}";
    }



    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((importeMovimiento == null) ? 0 : importeMovimiento.hashCode());
        result = prime * result + ((medioPago == null) ? 0 : medioPago.hashCode());
        result = prime * result + ((comentarioMovimiento == null) ? 0 : comentarioMovimiento.hashCode());
        result = prime * result + ((fechaAltaMovimiento == null) ? 0 : fechaAltaMovimiento.hashCode());
        result = prime * result + ((fechaBajaMovimiento == null) ? 0 : fechaBajaMovimiento.hashCode());
        result = prime * result + ((comprobantes == null) ? 0 : comprobantes.hashCode());
        result = prime * result + ((cuenta == null) ? 0 : cuenta.hashCode());
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
        Movimiento other = (Movimiento) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (importeMovimiento == null) {
            if (other.importeMovimiento != null)
                return false;
        } else if (!importeMovimiento.equals(other.importeMovimiento))
            return false;
        if (medioPago == null) {
            if (other.medioPago != null)
                return false;
        } else if (!medioPago.equals(other.medioPago))
            return false;
        if (comentarioMovimiento == null) {
            if (other.comentarioMovimiento != null)
                return false;
        } else if (!comentarioMovimiento.equals(other.comentarioMovimiento))
            return false;
        if (fechaAltaMovimiento == null) {
            if (other.fechaAltaMovimiento != null)
                return false;
        } else if (!fechaAltaMovimiento.equals(other.fechaAltaMovimiento))
            return false;
        if (fechaBajaMovimiento == null) {
            if (other.fechaBajaMovimiento != null)
                return false;
        } else if (!fechaBajaMovimiento.equals(other.fechaBajaMovimiento))
            return false;
        if (comprobantes == null) {
            if (other.comprobantes != null)
                return false;
        } else if (!comprobantes.equals(other.comprobantes))
            return false;
        if (cuenta == null) {
            if (other.cuenta != null)
                return false;
        } else if (!cuenta.equals(other.cuenta))
            return false;
        return true;
    }




}