package com.example.ch4codeyourself.v2.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
//@Entity // jpa에선 이 어노테이션이 필요하다
@Table(name = "posts")  // table과 이름을 통해 매칭시킨다
public class Post {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String body;
}

// DB의 테이블(posts)과 실제로 연결된다

//엔티티에 기본 키가 없습니다 
//지속성 엔티티 'Post'에는 기본 키가 포함되어야 합니다

// @Id 어노테이션으로 기본키를 알려줘야 한다
// @Id // import jakarta.persistence.Id;
// @GeneratedValue(strategy = GenerationType.IDENTITY)
