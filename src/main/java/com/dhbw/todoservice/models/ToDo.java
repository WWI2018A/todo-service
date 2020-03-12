package com.dhbw.todoservice.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@Document(collection = "todo-service-todos")
public class ToDo {
    @Id
    private String id;
    private String userId;
    private String listId;
    private Date createdOn;
    private Date dueUntil;
    private ToDoStatus status;
    private String content;

    public ToDo(String userId, String listId, Date createdOn,
                Date dueUntil, ToDoStatus status, String content) {
        this.userId = userId;
        this.listId = listId;
        this.createdOn = createdOn;
        this.dueUntil = dueUntil;
        this.status = status;
        this.content = content;
    }
}
