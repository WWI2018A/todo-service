package com.dhbw.todoservice.repositories;

import com.dhbw.todoservice.models.TodoList;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TodoListRepository extends MongoRepository<TodoList, String> {
}
