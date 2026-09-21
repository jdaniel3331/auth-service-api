package com.jdaniel3331.authserviceapi.dtos;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public record ApiResponse<T>(
        String timestamp,
        String status,
        int statusCode,
        String message,
        T data
) {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

    public ApiResponse(String status, String message, int statusCode) {
        this(LocalDateTime.now().format(FORMATTER), status, statusCode, message, null);
    }

    public ApiResponse(String status, String message, int statusCode, T data) {
        this(LocalDateTime.now().format(FORMATTER), status, statusCode, message, data);
    }
}
