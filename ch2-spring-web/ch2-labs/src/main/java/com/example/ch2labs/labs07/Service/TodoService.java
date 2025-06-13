package com.example.ch2labs.labs07.Service;

import com.example.ch2labs.labs07.dto.TodoCreateRequest;
import com.example.ch2labs.labs07.Repository.TodoRepository;
import com.example.ch2labs.labs07.dto.TodoResponse;
import com.example.ch2labs.labs07.entity.Todo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TodoService {

    private final TodoRepository repository;
    public TodoResponse toResponse(Todo todo) {
        return new TodoResponse(todo.getId(), todo.getTitle(), todo.isCompleted());
    }

    public TodoResponse createTodo(TodoCreateRequest request) {
        Todo newTodo = repository.save(new Todo(null, request.getTitle(), request.getCompleted()));

        return toResponse(newTodo);
    }

    public List<TodoResponse> getAllTodos() {
        return repository.findAll().stream().map(this::toResponse).toList();
    }

    public TodoResponse updateTodo(TodoCreateRequest request, Long id) throws RuntimeException {
        Todo target = repository.findById(id).orElseThrow(() -> new RuntimeException("Todo Not Found"));

        target.setTitle(request.getTitle());
        target.setCompleted(request.getCompleted());

        return toResponse(target);
    }

    public void deleteTodo(Long id) throws RuntimeException {
        Todo target = repository.findById(id).orElseThrow(() -> new RuntimeException("Todo Not Found"));

        repository.delete(target.getId());
    }
}
