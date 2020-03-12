package com.dhbw.todoservice.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "todo-service-lists")
public class ToDoList {
    @Id
    private String id;
    private String name;

    public ToDoList(String name) {
        this.name = name;
    }
}
