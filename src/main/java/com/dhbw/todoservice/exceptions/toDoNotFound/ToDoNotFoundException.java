package com.dhbw.todoservice.exceptions.toDoNotFound;

public class ToDoNotFoundException extends RuntimeException  {
    public ToDoNotFoundException(String id) {
        super("Could not find toDo with id " + id);
    }
}
