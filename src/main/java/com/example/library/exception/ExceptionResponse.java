package com.example.library.exception;



import java.time.LocalDateTime;

public record ExceptionResponse(String message, LocalDateTime time) {
}
