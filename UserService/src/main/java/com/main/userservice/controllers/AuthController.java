package com.main.userservice.controllers;

import com.main.userservice.UserServiceApplication;
import com.main.userservice.dtos.*;
import com.main.userservice.services.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    AuthService authService;
    public AuthController(AuthService authService){
        this.authService=authService;
    }

    @PostMapping("/login")
    public ResponseEntity<UserDto> login(@RequestBody LoginRequestDto request){
        UserDto userDto = authService.login(request.getEmail(), request.getPassword());
        return new ResponseEntity<>(userDto,HttpStatus.OK);
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(@RequestBody LogoutRequestDto request){
        return null;
    }

    @PostMapping("/signup")
    public ResponseEntity<UserDto> signup(@RequestBody SignupRequestDto request){
        UserDto userDto= authService.signUp(request.getEmail(), request.getPassword());
        return new ResponseEntity<>(userDto,HttpStatus.OK);
    }

    @PostMapping("/validate")
    public ResponseEntity<Void> validateToken(@RequestBody ValidateTokenRequestDto request){
        return null;
    }

}
