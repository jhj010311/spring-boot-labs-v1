package com.example.ch4codeyourself.v4.dto.post;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostSearchRequest {
    private String keyword = "";
    private String author = "";
    private LocalDateTime CreatedAt;
    private String sort;

    private int page = 0;
    private int size = 10;
}
