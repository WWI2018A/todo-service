package com.dhbw.todoservice.repositories;

import com.dhbw.todoservice.models.TodoList;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface TodoListRepository extends MongoRepository<TodoList, String> {
    public List<TodoList> findAllByUserId(String userId);
    public Optional<TodoList> findByIdAndUserId(String id, String userId);
}
