package com.cuentacorrienteapp.cuentacorrienteapp.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
@Entity
@Table(name="users")
public class User {

    @Id
    @GeneratedValue ( strategy = GenerationType.UUID)
    private String id;

    @NotBlank
    private String password;

    @NotBlank
    private String name;


    @Email
    private String email;

    


}