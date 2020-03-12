package com.dhbw.todoservice.exceptions.toDoNotFound;

public class TodoNotFoundException extends RuntimeException  {
    public TodoNotFoundException(String id) {
        super("Could not find todo with id " + id);
    }
}
