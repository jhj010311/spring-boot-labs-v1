package com.example.ch2labs.labs07.Repository;

import com.example.ch2labs.labs07.dto.TodoResponse;
import com.example.ch2labs.labs07.entity.Todo;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class TodoRepository {
    private final Map<Long, Todo> store = new HashMap<>();
    private Long sequence = 0L;


    public Todo save(Todo todo) {
        todo.setId(++sequence);
        store.put(todo.getId(), todo);

        return todo;
    }

    public List<Todo> findAll() {
        return new ArrayList<>(store.values());
    }

    public Optional<Todo> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    public void delete(Long id) {
        store.remove(id);
    }
}
