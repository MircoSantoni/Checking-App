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
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="movimientos")
public class Movimiento {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="importe_movimiento")
    private Long importeMovimiento;

    @Column(name="medio_pago")
    private String medioPago;

    @Column(name="comentario_movimiento")
    private String comentarioMovimiento;

    @Column(name="fecha_alta_movimiento")
    private LocalDate fechaAltaMovimiento;

    @Column(name="fecha_baja_movimiento")
    private LocalDate fechaBajaMovimiento;

    @OneToMany( cascade = CascadeType.ALL , orphanRemoval = true , mappedBy = "movimiento")
    private Set<ComprobanteMovimiento> comprobanteMovimientos;

    @ManyToOne
    @JoinColumn(name="movimiento_id")
    private Cuenta cuenta;

    public Movimiento() {
        comprobanteMovimientos= new HashSet<>();
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




}