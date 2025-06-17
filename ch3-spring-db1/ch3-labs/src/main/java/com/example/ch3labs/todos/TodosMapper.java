package com.example.ch3labs.todos;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TodosMapper {
    void save(Todos todos);

    List<Todos> search(TodosSearchRequest search);

    void edit(Todos todo);

    void delete(Long id);
}
