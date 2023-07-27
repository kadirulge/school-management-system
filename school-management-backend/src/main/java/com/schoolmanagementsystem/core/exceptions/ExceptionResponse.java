package com.schoolmanagementsystem.core.exceptions;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ExceptionResponse<T> {
    private LocalDateTime timestamp;
    private String type;
    private T message;

    public ExceptionResponse(String type, T message) {
        timestamp = LocalDateTime.now();
        this.type = type;
        this.message = message;
    }
}
