package com.dhbw.todoservice.repositories;

import com.dhbw.todoservice.models.ToDo;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface ToDoRepository extends MongoRepository<ToDo, String> {

}
