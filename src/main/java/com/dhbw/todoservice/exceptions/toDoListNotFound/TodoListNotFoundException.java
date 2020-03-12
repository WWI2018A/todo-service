package com.dhbw.todoservice.exceptions.toDoListNotFound;

public class TodoListNotFoundException extends RuntimeException {
    public TodoListNotFoundException(String id) {
        super("Could not find toDo List with id " + id);
    }
}
