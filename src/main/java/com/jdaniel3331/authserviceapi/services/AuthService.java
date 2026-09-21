package com.jdaniel3331.authserviceapi.services;

import com.jdaniel3331.authserviceapi.dtos.LoginRequest;
import com.jdaniel3331.authserviceapi.dtos.LoginResponse;

public interface AuthService {

    LoginResponse login(LoginRequest loginRequest);
}
