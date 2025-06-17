package com.example.ch3labs.todos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Todos {
    private Long id;
    private String title;
    private boolean completed;
}
