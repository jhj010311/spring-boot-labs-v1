package com.example.ch3labs.todos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TodoUpdateRequest {
    private Long id;
    private String title;
    private boolean completed;

    public Todos toDomain() {
        Todos todo = new Todos();

        todo.setId(id);
        todo.setTitle(title);
        todo.setCompleted(completed);

        return todo;
    }
}
