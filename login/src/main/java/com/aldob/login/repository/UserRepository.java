package com.aldob.login.repository;

import com.aldob.login.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findByCorreoAndContrasena(String correo, String contrasena);

    UserEntity findByCorreo(String correo);

    UserEntity findByUsuario(String usuario);

}