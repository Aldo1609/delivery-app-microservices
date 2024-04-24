package com.aldob.login.service;

import com.aldob.login.config.ResponseMessage;
import com.aldob.login.dto.UserDTO;

import java.util.List;

public interface UserService {

    List<UserDTO> getAllUsers();
    UserDTO authenticate(String correo, String contrasena);
    UserDTO register(UserDTO userDTO);

    boolean checkIfEmailExists(String correo);
    boolean checkIfUsuarioExists(String usuario);

    ResponseMessage updatePassword(String correo, String currentPassword, String newPassword);
}