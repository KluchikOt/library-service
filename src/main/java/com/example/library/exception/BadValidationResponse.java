package com.example.library.exception;

import java.time.LocalDateTime;
import java.util.Map;

public record BadValidationResponse(Map<String,String> message, LocalDateTime time) {
}
