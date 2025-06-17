package com.example.ch3labs.todos;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/todos")
@RequiredArgsConstructor
public class TodosController {

    private final TodosService service;

    @PostMapping
    public ResponseEntity<TodosResponse> createTodo(@RequestBody TodoCreateRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createTodo(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TodosResponse> getTodo(@PathVariable Long id) {

        return ResponseEntity.ok(service.getAllTodos(id));
    }
}
