package com.ibrahim.entitymanagerdemo.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ErrorDetails {
    private LocalDateTime timestamp;
    private String status;
    private int httpStatusCode;
    private String message;
    private String path;

    public ErrorDetails() {
        this.timestamp = LocalDateTime.now();
        this.status = "error";
    }

    public ErrorDetails(int httpStatusCode, String message, String path) {
        this.timestamp = LocalDateTime.now();
        this.status = "error";
        this.httpStatusCode = httpStatusCode;
        this.message = message;
        this.path = path;
    }
}
