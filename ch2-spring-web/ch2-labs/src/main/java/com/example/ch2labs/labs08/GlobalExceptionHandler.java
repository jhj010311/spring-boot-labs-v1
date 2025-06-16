package com.example.ch2labs.labs08;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidation(MethodArgumentNotValidException e) {
        Map<String, String> errors = new HashMap<>();

        e.getBindingResult().getFieldErrors().forEach(error -> {
            errors.put(error.getField(), error.getDefaultMessage());
        });

        e.getBindingResult().getGlobalErrors().forEach(error ->
                errors.put("time", error.getDefaultMessage())  // ✅ 클래스 수준 오류 추가
        );

        Map<String, Object> response = new HashMap<>();
        response.put("error", errors);

        return ResponseEntity.badRequest().body(response);
    }
}
