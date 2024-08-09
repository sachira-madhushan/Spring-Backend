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
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    //@Route POST /user/register
    //@Access Public
    //@Use Register a new user
    @Autowired
    private UserService userService;
    @PostMapping("/register")
    public ResponseEntity<RegisterDTO> register(@RequestBody UserDTO userDTO) {
        RegisterDTO user=userService.registerUser(userDTO);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    //@Route POST /user/login
    //@Access Public
    //@Use Login user
    @PostMapping("/login")
    public ResponseEntity<LoginMessageDTO> login(@RequestBody LoginDTO loginDTO) {
        LoginMessageDTO message=userService.loginUser(loginDTO);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    //@Route GET /user/{id}
    //@Access Public
    //@Use Get user by id
    @GetMapping("{id}")
    public ResponseEntity<Object> getUser(@PathVariable int id) {
        Object user=userService.getUserByID(id);
        if(user!=null){
            return new ResponseEntity<>(user, HttpStatus.OK);
        }else{
            return new ResponseEntity<>("User not found",HttpStatus.NOT_FOUND);
        }
    }
}
