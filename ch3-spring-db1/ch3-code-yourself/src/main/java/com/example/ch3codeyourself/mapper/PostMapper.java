package com.example.ch3codeyourself.mapper;

import com.example.ch3codeyourself.domain.Post;
import com.example.ch3codeyourself.dto.PostSearchRequest;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

// @Mapper : myBatis 매퍼
// src/main/resources/mapper/PostMapper.xml에 정의
@Mapper
public interface PostMapper {
    void save(Post post);

    List<Post> findAll();

    Post findOne(Long id);

    int edit(Post post);

    int delete(Long id);

    List<Post> search(PostSearchRequest search);

    int count(PostSearchRequest page);
}
