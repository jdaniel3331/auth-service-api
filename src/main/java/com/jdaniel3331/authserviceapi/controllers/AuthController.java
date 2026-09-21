package com.jdaniel3331.authserviceapi.controllers;

import com.jdaniel3331.authserviceapi.dtos.ApiResponse;
import com.jdaniel3331.authserviceapi.dtos.LoginRequest;
import com.jdaniel3331.authserviceapi.dtos.LoginResponse;
import com.jdaniel3331.authserviceapi.services.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping
    public String hola(){
        return "Hola";
    }

    @PostMapping("/register")
    public void register(){

    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<LoginResponse>> login(@RequestBody LoginRequest  loginRequest) {
        LoginResponse r = authService.login(loginRequest);
        ApiResponse<LoginResponse> response = new ApiResponse<>(HttpStatus.OK.name(),"Login exitoso", HttpStatus.OK.value(),r);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/refresh")
    public void refresh(){

    }
}
