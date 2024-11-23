package com.cuentacorrienteapp.cuentacorrienteapp.entities;

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
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@Entity
@Table(name="cuentas")
public class Cuenta {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private Long saldo;
    private String name;
    private boolean isValid;
    private String userId;

    @Column(name="nombre_proveedor")
    private String nombreProveedor;

    @Column(name="numero_celular")
    private String numeroCelular;
    
    @Column(name="email_proveedor")
    private String emailProveedor;
    
    @Column(name="direccion_proveedor")
    private String direccionProveedor;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "cuenta")
    private Set<Movimiento> movimientos = new HashSet<>();

    public void addMovimiento(Movimiento movimiento) {
        movimientos.add(movimiento);
        movimiento.setCuenta(this);
    }

    public void removeMovimiento(Movimiento movimiento) {
        movimientos.remove(movimiento);
        movimiento.setCuenta(null);
    }
}