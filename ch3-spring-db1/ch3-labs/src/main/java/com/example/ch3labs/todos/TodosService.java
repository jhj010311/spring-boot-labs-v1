package com.example.ch3labs.todos;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TodosService {

    private final TodosMapper mapper;


    public TodosResponse createTodo(TodoCreateRequest request) {

        Todos todos = request.toDomain();

        mapper.save(todos);
    }
}
