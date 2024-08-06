package com.sachira.spring_backend.controller;

import com.sachira.spring_backend.dto.LoginDTO;
import com.sachira.spring_backend.dto.LoginMessageDTO;
import com.sachira.spring_backend.dto.RegisterDTO;
import com.sachira.spring_backend.dto.UserDTO;
import com.sachira.spring_backend.service.UserService;
import jakarta.persistence.Entity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    @PostMapping("/register")
    public ResponseEntity<RegisterDTO> register(@RequestBody UserDTO userDTO) {
        RegisterDTO user=userService.registerUser(userDTO);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }
    @PostMapping("/login")
    public ResponseEntity<LoginMessageDTO> login(@RequestBody LoginDTO loginDTO) {
        LoginMessageDTO message=userService.loginUser(loginDTO);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
