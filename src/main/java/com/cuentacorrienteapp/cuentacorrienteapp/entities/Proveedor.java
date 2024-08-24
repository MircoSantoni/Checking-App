package com.cuentacorrienteapp.cuentacorrienteapp.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="proveedores")
public class Proveedor {

    @Id
    @GeneratedValue ( strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="nombre_proveedor")
    private String nombreProveedor;

    @Column(name="numero_celular")
    private Long numeroCelular;

    @Column(name="email_proveedor")
    private String emailProveedor;

    @Column(name="direccion_proveedor")
    private String direccionProveedor;



    public Proveedor() {
    }


    public Proveedor(Long id, String nombreProveedor, Long numeroCelular, String emailProveedor,
            String direccionProveedor) {
        this.id = id;
        this.nombreProveedor = nombreProveedor;
        this.numeroCelular = numeroCelular;
        this.emailProveedor = emailProveedor;
        this.direccionProveedor = direccionProveedor;
    }


    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getNombreProveedor() {
        return nombreProveedor;
    }
    public void setNombreProveedor(String nombreProveedor) {
        this.nombreProveedor = nombreProveedor;
    }
    public Long getNumeroCelular() {
        return numeroCelular;
    }
    public void setNumeroCelular(Long numeroCelular) {
        this.numeroCelular = numeroCelular;
    }
    public String getEmailProveedor() {
        return emailProveedor;
    }
    public void setEmailProveedor(String emailProveedor) {
        this.emailProveedor = emailProveedor;
    }
    public String getDireccionProveedor() {
        return direccionProveedor;
    }
    public void setDireccionProveedor(String direccionProveedor) {
        this.direccionProveedor = direccionProveedor;
    }


    @Override
    public String toString() {
        return "{id=" + id + 
        ", nombreProveedor=" + nombreProveedor + 
        ", numeroCelular=" + numeroCelular + 
        ", emailProveedor=" + emailProveedor + 
        ", direccionProveedor=" + direccionProveedor + 
        "}";
    }



}
