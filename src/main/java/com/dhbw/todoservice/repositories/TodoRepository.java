package com.dhbw.todoservice.repositories;

import com.dhbw.todoservice.models.Todo;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface TodoRepository extends MongoRepository<Todo, String> {

}
