package com.dhbw.todoservice.repositories;

import com.dhbw.todoservice.models.Todo;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;


public interface TodoRepository extends MongoRepository<Todo, String> {
    public List<Todo> findByListId(String listId);
    public void deleteByListId(String listId);
    public List<Todo> findByListIdAndUserId(String listId, String userId);
    public List<Todo> findAllByUserId(String userId);
    public Optional<Todo> findByIdAndUserId(String id, String userId);
}
