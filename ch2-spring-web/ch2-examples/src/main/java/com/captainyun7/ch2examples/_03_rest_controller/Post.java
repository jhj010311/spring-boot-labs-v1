package com.captainyun7.ch2examples._03_rest_controller;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// Jackson 라이브러리가 JSON 변환 시 사용
@Getter
@Setter
@NoArgsConstructor         // 기본 생성자 추가 (JSON 직렬화를 위해 필요)
public class Post {
    private Long id;
    private String title;
    private String body;

    public Post(String title, String body) {
        this.title = title;
        this.body = body;
    }
}