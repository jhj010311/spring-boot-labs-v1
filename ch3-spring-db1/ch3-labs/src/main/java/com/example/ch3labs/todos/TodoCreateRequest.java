package com.example.ch3labs.todos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TodoCreateRequest {
    private String title;
    private boolean completed;

    public Todos toDomain() {
        Todos todos = new Todos();

        todos.setTitle(title);
        todos.setCompleted(completed);

        return todos;
    }
}
