package com.ale.englishnote.util;

import org.springframework.data.mapping.PropertyReferenceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class AppExceptionHandler {
    @ExceptionHandler({ PropertyReferenceException.class, IllegalArgumentException.class, NullPointerException.class})
    public ResponseEntity handleRequest(Exception e) {
        e.printStackTrace();
        return new ResponseEntity<>(MessageContent.BAD_QUERY, HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

    @ExceptionHandler({ AppException.class})
    public ResponseEntity handleAppException(AppException e) {
        e.printStackTrace();
        return new ResponseEntity<>(e.getMessage(), e.getStatus());
    }

    @ExceptionHandler({ Exception.class })
    public ResponseEntity handleException(Exception e) {
        e.printStackTrace();
        return ResponseEntity.status(500).body(MessageContent.UNKNOWN_ERROR);
    }
}
