package com.dhbw.todoservice.repositories;

import com.dhbw.todoservice.models.Todo;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;


public interface TodoRepository extends MongoRepository<Todo, String> {
    public List<Todo> findByListId(String listId);
}
