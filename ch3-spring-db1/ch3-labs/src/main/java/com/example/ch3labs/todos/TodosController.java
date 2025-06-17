package com.example.ch3labs.todos;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/todos")
@RequiredArgsConstructor
public class TodosController {

    private final TodosService service;

    @PostMapping
    public ResponseEntity<TodosResponse> createTodo(@RequestBody TodoCreateRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createTodo(request));
    }

    @GetMapping
    public ResponseEntity<List<TodosResponse>> searchTodos(TodosSearchRequest search) {

        return ResponseEntity.ok(service.searchTodos(search));
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> editTodo(@PathVariable Long id, @RequestBody TodoUpdateRequest request) {
        service.editTodo(id, request);

        return ResponseEntity.ok("수정 완료");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTodo(@PathVariable Long id) {
        service.deleteTodo(id);

        return ResponseEntity.noContent().build();
    }
}
