package com.main.userservice.controllers;

import com.main.userservice.dtos.SetUserRoleRequestDto;
import com.main.userservice.dtos.UserDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserDetails(@PathVariable("id") Long userId){
        return null;
    }
    public ResponseEntity<UserDto> createUserRoles(@PathVariable("id") Long userId, @RequestBody SetUserRoleRequestDto request){
        return null;
    }
}
