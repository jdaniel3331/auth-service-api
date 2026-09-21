package com.jdaniel3331.authserviceapi.services.impl;

import com.jdaniel3331.authserviceapi.dtos.LoginRequest;
import com.jdaniel3331.authserviceapi.dtos.LoginResponse;
import com.jdaniel3331.authserviceapi.services.AuthService;
import com.jdaniel3331.authserviceapi.utils.JwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final UserDetailsServiceImpl userDetailsService;
    private final JwtService jwtService;

    public AuthServiceImpl(AuthenticationManager authenticationManager, UserDetailsServiceImpl userDetailsService, JwtService jwtService) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.jwtService = jwtService;
    }

    @Override
    public LoginResponse login(LoginRequest loginRequest){
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.email(), loginRequest.password()));
        UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequest.email());

        String jwt = jwtService.generateToken(userDetails);
        return new LoginResponse(jwt, "refreshToken");
    }
}
