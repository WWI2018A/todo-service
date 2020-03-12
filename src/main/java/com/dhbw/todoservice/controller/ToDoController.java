package com.dhbw.todoservice.controller;

import com.dhbw.todoservice.exceptions.toDoListNotFound.ToDoListNotFoundException;
import com.dhbw.todoservice.exceptions.toDoNotFound.ToDoNotFoundException;
import com.dhbw.todoservice.repositories.ToDoListRepository;
import com.dhbw.todoservice.repositories.ToDoRepository;
import com.dhbw.todoservice.models.ToDo;
import com.dhbw.todoservice.models.ToDoList;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
public class ToDoController {

    private final ToDoRepository toDoRepository;
    private final ToDoListRepository toDoListRepository;

    ToDoController(ToDoRepository toDoRepository, ToDoListRepository toDoListRepository) {
        this.toDoRepository = toDoRepository;
        this.toDoListRepository = toDoListRepository;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/")
    public List<ToDo> toDos() {
        return toDoRepository.findAll();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ToDo toDoById(@PathVariable String id) {
        return toDoRepository.findById(id)
                .orElseThrow(() -> new ToDoNotFoundException(id));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/toDoLists")
    public List<ToDoList> toDoLists() {
        return toDoListRepository.findAll();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/toDoLists/{id}")
    public ToDoList toDoListById(@PathVariable String id) {
        return toDoListRepository.findById(id)
                .orElseThrow(() -> new ToDoListNotFoundException(id));
    }
}
