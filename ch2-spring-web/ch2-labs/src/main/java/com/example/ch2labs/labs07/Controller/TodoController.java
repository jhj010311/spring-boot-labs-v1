package com.example.ch2labs.labs07.Controller;

import com.example.ch2labs.labs07.Service.TodoService;
import com.example.ch2labs.labs07.dto.TodoCreateRequest;
import com.example.ch2labs.labs07.dto.TodoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/todos")
@RequiredArgsConstructor
public class TodoController {
    private final TodoService service;


    // 1. 할 일 작성 (Create)
    @PostMapping
    public ResponseEntity<TodoResponse> createTodo(@RequestBody TodoCreateRequest request) {
        if(request.getTitle() == null || request.getTitle().isBlank() || request.getCompleted() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new TodoResponse(null, "title 혹은 completed 누락", false));
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(service.createTodo(request));
    }


    // 2. 할 일 전체 목록 조회 (Read)
    @GetMapping
    public ResponseEntity<List<TodoResponse>> getAllTodos() {
        return ResponseEntity.ok(service.getAllTodos());
    }


    // 3. 할 일 수정 (Update)
    @PutMapping("/{id}")
    public ResponseEntity<TodoResponse> updateTodo(@RequestBody TodoCreateRequest request, @PathVariable Long id) {
        try {
            return ResponseEntity.ok(service.updateTodo(request, id));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new TodoResponse(null, e.getMessage(), false));
        }

    }


    // 4. 할 일 삭제 (Delete)
    @DeleteMapping("/{id}")
    public ResponseEntity<TodoResponse> deleteTodo(@PathVariable Long id) {
        try {
            service.deleteTodo(id);

            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new TodoResponse(null, e.getMessage(), false));
        }

    }
}
