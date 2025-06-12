package com.captainyun7.ch2codeyourself._03_rest_controller;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor  // 기본 생성자 lombok <-> @AllArgsConstructor
public class Post {
    private Long id;
    private String title;
    private String body;

    public Post(String title, String body) {
        this.title = title;
        this.body = body;
    }
}
