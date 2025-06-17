package com.example.ch3labs.todos;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TodosMapper {
    void save(Todos todos);
}
