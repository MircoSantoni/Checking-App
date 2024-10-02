package com.cuentacorrienteapp.cuentacorrienteapp.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@Entity
@Table(name="proveedores")
public class Proveedor {

    @Id
    @GeneratedValue ( strategy = GenerationType.UUID)
    private String id;

    @NotBlank
    @Column(name="nombre_proveedor")
    private String nombreProveedor;

    @NotNull
    @Column(name="numero_celular")
    private String numeroCelular;

    @Email
    @Column(name="email_proveedor")
    private String emailProveedor;

    @NotBlank
    @Column(name="direccion_proveedor")
    private String direccionProveedor;

    private boolean isValid;

}
