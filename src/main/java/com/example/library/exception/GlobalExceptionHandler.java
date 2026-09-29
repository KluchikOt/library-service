package com.example.library.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mapping.PropertyReferenceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.ErrorResponse;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleResourceNotFound(ResourceNotFoundException ex) {
        ExceptionResponse resp = new ExceptionResponse(ex.getMessage(), LocalDateTime.now());
        return new ResponseEntity<>(resp, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({ResourceAlreadyExistException.class, AllBooksOnHandsException.class})
    public ResponseEntity<ExceptionResponse> handleConflict(RuntimeException ex) {
        ExceptionResponse resp = new ExceptionResponse(ex.getMessage(), LocalDateTime.now());
        return new ResponseEntity<>(resp, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<BadValidationResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {

        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage())
        );

        BadValidationResponse resp = new BadValidationResponse(errors, LocalDateTime.now());
        return new ResponseEntity<>(resp, ex.getStatusCode());

    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponse> handleException(Exception ex) {
        log.error("Unhandled exception", ex);
        ExceptionResponse resp = new ExceptionResponse("Непредвиденная ошибка", LocalDateTime.now());
        return new ResponseEntity<>(resp, HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ExceptionResponse> handleWrongJson(HttpMessageNotReadableException ex) {
        ExceptionResponse resp = new ExceptionResponse("Некорректный JSON", LocalDateTime.now());
        return new ResponseEntity<>(resp, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ExceptionResponse> handleWrongType(MethodArgumentTypeMismatchException ex) {
        ExceptionResponse resp = new ExceptionResponse("Некорректный формат параметра", LocalDateTime.now());
        return new ResponseEntity<>(resp, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ExceptionResponse> handleMethodNotSupported(HttpRequestMethodNotSupportedException ex) {
        ExceptionResponse resp = new ExceptionResponse(ex.getMessage(), LocalDateTime.now());
        return new ResponseEntity<>(resp, HttpStatus.METHOD_NOT_ALLOWED);
    }

    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<ExceptionResponse> handleNoResource(NoResourceFoundException ex) {
        ExceptionResponse resp = new ExceptionResponse("Ресурс не найден", LocalDateTime.now());
        return new ResponseEntity<>(resp, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(PropertyReferenceException.class)
    public ResponseEntity<ExceptionResponse> handlePropertyRef(PropertyReferenceException ex) {
        ExceptionResponse resp = new ExceptionResponse("Неизвестное поле: " + ex.getPropertyName(), LocalDateTime.now());
        return new ResponseEntity<>(resp, HttpStatus.BAD_REQUEST);
    }
}

