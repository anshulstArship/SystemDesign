package com.main.userservice.controllers;

import com.main.userservice.dtos.RoleRequestDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/roles")
public class RoleController {

    @PostMapping
    public ResponseEntity<Void> createRole(@RequestBody RoleRequestDto request){
        return null;
    }
}
