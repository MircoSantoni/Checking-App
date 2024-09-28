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
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
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
    private Long numeroCelular;

    @Email
    @Column(name="email_proveedor")
    private String emailProveedor;

    @NotBlank
    @Column(name="direccion_proveedor")
    private String direccionProveedor;




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
