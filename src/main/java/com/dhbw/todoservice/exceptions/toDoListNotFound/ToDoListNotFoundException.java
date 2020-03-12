package com.dhbw.todoservice.exceptions.toDoListNotFound;

public class ToDoListNotFoundException extends RuntimeException {
    public ToDoListNotFoundException(String id) {
        super("Could not find toDo List with id " + id);
    }
}
