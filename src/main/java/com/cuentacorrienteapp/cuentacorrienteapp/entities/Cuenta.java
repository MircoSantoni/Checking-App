package com.cuentacorrienteapp.cuentacorrienteapp.entities;

import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor

@Entity
@Table(name="cuentas")
public class Cuenta {

    @Id
    @GeneratedValue ( strategy = GenerationType.UUID)
    private String id;

    private Long saldo;

    private String name;

    @OneToMany( cascade = CascadeType.ALL, orphanRemoval = true , mappedBy = "cuenta")
    private Set<Movimiento> movimiento;

    private boolean isValid;

    private String userId;

}
