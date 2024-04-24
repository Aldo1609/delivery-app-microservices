package com.aldob.login.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UserDTO {

    private Long id;
    @NotNull(message = "El usuario no puede ser nulo")
    @NotBlank(message = "El usuario no puede estar vacío")
    private String usuario;

    @NotNull(message = "El correo no puede ser nulo")
    @NotBlank(message = "El correo no puede estar vacío")
    private String correo;

    @NotNull(message = "La contraseña no puede ser nula")
    @NotBlank(message = "El contraseña no puede estar vacía")
    private String contrasena;

    private String currentPassword;

    private String rol;

}