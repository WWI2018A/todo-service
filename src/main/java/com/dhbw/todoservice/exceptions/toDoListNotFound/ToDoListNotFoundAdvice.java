package com.dhbw.todoservice.exceptions.toDoListNotFound;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

public class ToDoListNotFoundAdvice {
    @ResponseBody
    @ExceptionHandler(ToDoListNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String toDoListNotFoundHandler(ToDoListNotFoundException ex) {
        return ex.getMessage();
    }
}
