package com.example.ch4codeyourself.v4.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "posts")
public class Post {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String body;

    private String author;

    @CreationTimestamp
    private LocalDateTime createdAt;



    // 포함관계 어노테이션
    // mappedBy : 상대측의 "컬럼명"이 일치하는 것과 매칭하겠다
    @OneToMany(mappedBy = "post", fetch = FetchType.LAZY)
    private List<Comment> comments = new ArrayList<>();
    
    // 우리가 제어할 수 없이 jpa가 관리하기 때문에 편리하지만
    // 또한 위험하기도 하다
}
