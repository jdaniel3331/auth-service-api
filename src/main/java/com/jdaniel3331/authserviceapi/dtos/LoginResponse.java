package com.jdaniel3331.authserviceapi.dtos;

public record LoginResponse(
        String jwt,
        String refreshToken
) {
}
