package com.example.ch2labs.labs09;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExternalTodosResponse {
    private Long userId;
    private Long id;
    private String title;
    private boolean completed;
}
