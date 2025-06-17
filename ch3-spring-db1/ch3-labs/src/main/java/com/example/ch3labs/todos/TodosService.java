package com.example.ch3labs.todos;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TodosService {

    private final TodosMapper mapper;


    public TodosResponse createTodo(TodoCreateRequest request) {

        Todos todos = request.toDomain();

        mapper.save(todos);

        return TodosResponse.from(todos);
    }

    public List<TodosResponse> searchTodos(TodosSearchRequest search) {
        List<TodosResponse> todos =mapper.search(search).stream()
                .map(todo -> TodosResponse.from(todo)).toList();

        return todos;
    }

    public void editTodo(Long id, TodoUpdateRequest request) {
        Todos todo = request.toDomain();
        todo.setId(id);

        mapper.edit(todo);
    }

    public void deleteTodo(Long id) {
        mapper.delete(id);
    }
}
