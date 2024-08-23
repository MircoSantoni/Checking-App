package com.cuentacorrienteapp.cuentacorrienteapp.entities;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="cuenta")
public class Cuenta {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="saldo_cuenta")
    private Long saldoCuenta;



    public Cuenta() {
    }

    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }

    public Long getSaldoCuenta() {
        return saldoCuenta;
    }

    public void setSaldoCuenta(Long saldoCuenta) {
        this.saldoCuenta = saldoCuenta;
    }

}
