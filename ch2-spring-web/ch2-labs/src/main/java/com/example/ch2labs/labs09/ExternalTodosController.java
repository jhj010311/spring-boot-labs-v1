package com.example.ch2labs.labs09;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/external-todos")
@RequiredArgsConstructor
public class ExternalTodosController {

    private final ExternalTodosService service;

    @GetMapping("/{id}")
    public ResponseEntity<ExternalTodosResponse> getTodo(@PathVariable Long id) {

        return ResponseEntity.ok(service.getAllTodos(id));
    }
}
