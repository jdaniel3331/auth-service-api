package com.jdaniel3331.authserviceapi.dtos;

public record LoginRequest(
        String email,
        String password
) {
}
