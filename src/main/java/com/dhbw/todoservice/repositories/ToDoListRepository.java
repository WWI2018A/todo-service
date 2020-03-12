package com.dhbw.todoservice.repositories;

import com.dhbw.todoservice.models.ToDoList;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ToDoListRepository extends MongoRepository<ToDoList, String> {
}
