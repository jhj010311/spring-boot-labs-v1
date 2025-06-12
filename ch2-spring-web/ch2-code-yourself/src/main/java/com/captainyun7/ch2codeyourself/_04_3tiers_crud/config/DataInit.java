package com.captainyun7.ch2codeyourself._04_3tiers_crud.config;

import com.captainyun7.ch2codeyourself._04_3tiers_crud.domain.Post;
import com.captainyun7.ch2codeyourself._04_3tiers_crud.repository.PostRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInit {

    private final PostRepository repository;

    // 생명주기 복습
    // 생성자 -> 초기화 -> ... -> 소멸

    @PostConstruct
    public void init() {
        repository.save(new Post(null,"가데이터 title", "가데이터 body"));
        repository.save(new Post(null,"가데이터 title2", "가데이터 body2"));
        repository.save(new Post(null,"가데이터 title3", "가데이터 body3"));
    }
}
