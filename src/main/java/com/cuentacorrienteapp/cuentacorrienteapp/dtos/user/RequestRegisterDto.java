package com.cuentacorrienteapp.cuentacorrienteapp.dtos.user;

import jakarta.validation.constraints.*;

public record RequestRegisterDto(   
    
@NotNull(message = "El nombre para registrarse es obligario y no puede estar vacio")
@Pattern( regexp = "^[a-zA-ZÀ-ÿ\\s]+$", message="El nombre no puede contener caracteres especiales")
String name,

@NotNull(message = "El apellido para registrarse es obligatorio y no puede estar vacio")
@Pattern( regexp = "^[a-zA-ZÀ-ÿ\\s]+$", message="El apellido no puede contener caracteres especiales")
String surname,

@NotNull( message = "El cuit para registrarse es obligatorio y no puede estar vacio")
String cuit,

@NotNull(message = "El correo para registrarse es obligatorio y no puede estar vacio")
@Email( message = "El formato debe de ser el de un correo valido")
String email,

@NotNull( message = "El celular para registrarse es obligatorio y no puede estar vacio")
String phone,   

@NotNull( message = "La contraseña para registrarse es obligatoria y no puede estar vacia")
@Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*()]).{8,}$",
message = "La contraseña debe tener al menos 8 caracteres, una letra minúscula, una letra mayúscula, un número y un carácter especial")
String password
) {}
