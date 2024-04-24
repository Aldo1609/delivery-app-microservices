package com.aldob.login.service.impl;

import com.aldob.login.config.ResponseMessage;
import com.aldob.login.dto.UserDTO;
import com.aldob.login.entity.UserEntity;
import com.aldob.login.exception.InvalidCredentialsException;
import com.aldob.login.exception.UserNotFoundException;
import com.aldob.login.mapper.UserMapper;
import com.aldob.login.repository.UserRepository;
import com.aldob.login.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<UserDTO> getAllUsers() {
        List<UserEntity> userEntities = userRepository.findAll();
        return userMapper.toDTO(userEntities);
    }

    @Override
    public UserDTO authenticate(String correo, String contrasena) {
        UserEntity userEntity = userRepository.findByCorreoAndContrasena(correo, contrasena);
        if (userEntity == null) {
            throw new InvalidCredentialsException("Correo o contraseña incorrectos");
        }
        return userMapper.toDTO(userEntity);
    }

    @Override
    public UserDTO register(UserDTO userDTO) {
        if (userDTO.getRol() == null) {
            userDTO.setRol("USER");
        }
        UserEntity userEntity = userMapper.toEntity(userDTO);
        UserEntity savedUserEntity = userRepository.save(userEntity);
        return userMapper.toDTO(savedUserEntity);
    }


    @Override
    public ResponseMessage updatePassword(String correo, String currentPassword, String newPassword) {
        UserEntity userEntity = userRepository.findByCorreo(correo);
        if (userEntity == null) {
            throw new UserNotFoundException("Usuario no encontrado");
        }
        if (!userEntity.getContrasena().equals(currentPassword)) {
            throw new IllegalArgumentException("La contraseña actual no es correcta");
        }
        if (userEntity.getContrasena().equals(newPassword)) {
            throw new IllegalArgumentException("La nueva contraseña no puede ser igual a la anterior");
        }
        userEntity.setContrasena(newPassword);
        userRepository.save(userEntity);
        return new ResponseMessage("Contraseña actualizada correctamente");
    }

    @Override
    public boolean checkIfEmailExists(String correo) {
        UserEntity userEntity = userRepository.findByCorreo(correo);
        return userEntity != null;
    }

    @Override
    public boolean checkIfUsuarioExists(String usuario) {
        UserEntity userEntity = userRepository.findByUsuario(usuario);
        return userEntity != null;
    }

}