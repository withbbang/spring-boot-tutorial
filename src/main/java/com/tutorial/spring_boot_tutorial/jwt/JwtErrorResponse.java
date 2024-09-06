package com.tutorial.spring_boot_tutorial.jwt;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class JwtErrorResponse {
    private int statusCode;
    private String message;
    private LocalDateTime timestamp;

    public JwtErrorResponse(int statusCode, String message, LocalDateTime timestamp) {
        this.statusCode = statusCode;
        this.message = message;
        this.timestamp = timestamp;
    }
}
