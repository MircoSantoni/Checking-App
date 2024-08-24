package com.cuentacorrienteapp.cuentacorrienteapp.entities;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="proveedores_cuentas")
public class ProveedorCuenta {
    
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="fecha_baja_logica")
    private LocalDate fechaBajaLogica;

    // private Cuenta idCuenta; //arregla esto mirco del futuro por favor es un desastre
    // private Proveedor idProveedor; // 13/8
    
    
    
    public ProveedorCuenta() {
    }
    
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getFechaBajaLogica() {
        return fechaBajaLogica;
    }

    public void setFechaBajaLogica(LocalDate fechaBajaLogica) {
        this.fechaBajaLogica = fechaBajaLogica;
    }

    // public Cuenta getIdCuenta() {
    //     return idCuenta;
    // }

    // public void setIdCuenta(Cuenta idCuenta) {
    //     this.idCuenta = idCuenta;
    // }

    // public Proveedor getIdProveedor() {
    //     return idProveedor;
    // }

    // public void setIdProveedor(Proveedor idProveedor) {
    //     this.idProveedor = idProveedor;
    // }




}
