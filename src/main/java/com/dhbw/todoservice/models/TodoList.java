package com.dhbw.todoservice.models;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/**
 * Represents a to-do-list in the database.
 */
@Data
@Document(collection = "todo_service_lists")
public class TodoList {
    @Id
    private String id;
    @CreatedDate
    private Date createdDate;
    @LastModifiedDate
    private Date lastModifiedDate;
    private String userId;
    private String name;

    public TodoList(String userId, String name) {
        this.userId = userId;
        this.name = name;
    }
}
