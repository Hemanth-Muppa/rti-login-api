package com.example.rtilogin.controller;

import com.example.rtilogin.dto.LoginRequest;
import com.example.rtilogin.dto.LoginResponse;
import com.example.rtilogin.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController

@RequestMapping("/api")

public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/login")

    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {

        return ResponseEntity.ok(authService.login(request));
    }
}
