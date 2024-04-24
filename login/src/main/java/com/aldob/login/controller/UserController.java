package com.aldob.login.controller;

import com.aldob.login.config.ResponseMessage;
import com.aldob.login.dto.UserDTO;
import com.aldob.login.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @PostMapping("/login")
    public ResponseEntity<UserDTO> login(@RequestBody UserDTO userDTO) {
        UserDTO authenticatedUser = userService.authenticate(userDTO.getCorreo(), userDTO.getContrasena());
        if (authenticatedUser != null) {
            return ResponseEntity.ok(authenticatedUser);
        } else {
            return ResponseEntity.status(401).build();
        }
    }

    @PostMapping("/register")
    public ResponseEntity<UserDTO> register(@Valid @RequestBody UserDTO userDTO) {
        UserDTO registeredUser = userService.register(userDTO);
        return ResponseEntity.ok(registeredUser);
    }

    @PutMapping("/updatePassword")
    public ResponseEntity<ResponseMessage> updatePassword(@RequestBody UserDTO userDTO) {
        ResponseMessage message = userService.updatePassword(userDTO.getCorreo(), userDTO.getCurrentPassword(), userDTO.getContrasena());
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @PostMapping("/checkEmail")
    public ResponseEntity<Boolean> checkIfEmailExists(@RequestBody Map<String, String> request) {
        String correo = request.get("email");
        boolean exists = userService.checkIfEmailExists(correo);
        return ResponseEntity.ok(exists);
    }

    @PostMapping("/checkUsername")
    public ResponseEntity<Boolean> checkIfUsernameExists(@RequestBody Map<String, String> request) {
        String usuario = request.get("usuario");
        boolean exists = userService.checkIfUsuarioExists(usuario);
        return ResponseEntity.ok(exists);
    }

}