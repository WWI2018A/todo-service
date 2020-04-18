package com.dhbw.todoservice.models;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.ZonedDateTime;
import java.util.Date;

/**
 * Represents a to-do in the database.
 */
@Data
@Document(collection = "todo_service_todos")
public class Todo {

    @Id
    private String id;
    @CreatedDate
    private ZonedDateTime createdDate;
    @LastModifiedDate
    private ZonedDateTime lastModifiedDate;
    private String userId;
    private String listId;
    private ZonedDateTime dueDate;
    private TodoStatus status = TodoStatus.UNCHECKED;
    private String content;

    public Todo(String userId, String listId, ZonedDateTime dueDate, String content) {
        this.userId = userId;
        this.listId = listId;
        this.dueDate = dueDate;
        this.content = content;
    }
}
