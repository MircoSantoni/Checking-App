package com.cuentacorrienteapp.cuentacorrienteapp.entities;


import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name="cuentas")
public class Cuenta {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;

    @Min( value= 0)
    private Long saldo;

    @NotNull
    private String name;

    @OneToMany( cascade = CascadeType.ALL, orphanRemoval = true , mappedBy = "cuenta")
    private Set<Movimiento> movimiento;

    public Cuenta() {
        movimiento = new HashSet<>();
    }

    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }

    public Long getSaldo() {
        return saldo;
    }

    public void setSaldo(Long saldo) {
        this.saldo = saldo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "{id=" + id + 
        ", saldo=" + saldo + 
        ", name=" + name + 
        "}";
    }




}
