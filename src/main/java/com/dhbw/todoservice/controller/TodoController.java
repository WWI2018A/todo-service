package com.dhbw.todoservice.controller;

import com.dhbw.todoservice.exceptions.toDoListNotFound.TodoListNotFoundException;
import com.dhbw.todoservice.exceptions.toDoNotFound.TodoNotFoundException;
import com.dhbw.todoservice.models.Todo;
import com.dhbw.todoservice.models.TodoList;
import com.dhbw.todoservice.repositories.TodoListRepository;
import com.dhbw.todoservice.repositories.TodoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
public class TodoController {

    // To do database (database = repository)
    private final TodoRepository todoRepository;

    // To do list database (database = repository)
    private final TodoListRepository todoListRepository;

    public TodoController(TodoRepository todoRepository, TodoListRepository todoListRepository) {
        this.todoRepository = todoRepository;
        this.todoListRepository = todoListRepository;
    }


    /**
     * Get all user to-dos from the to-do database
     *
     * @param listId
     * @param userId
     * @return all to-dos from user (200)
     */
    @RequestMapping(method = RequestMethod.GET, value = "/")
    public List<Todo> getTodos(@RequestParam(required = false) String listId, @RequestHeader("x-uid") String userId) {
        if (listId != null && !listId.isEmpty()) {
            return todoRepository.findByListIdAndUserId(listId, userId);
        }
        return todoRepository.findAllByUserId(userId);
    }

    /**
     * Get the user to-do by its id, if the to-do with the given id doesn't exist, throw an exception.
     *
     * @param userId
     * @param id
     * @return the to-do (200)
     */
    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public Todo getTodoById(@PathVariable String id, @RequestHeader("x-uid") String userId) {
        return todoRepository.findByIdAndUserId(id, userId)
                .orElseThrow(() -> new TodoNotFoundException(id));
    }

    /**
     * Create a new to-do and save it in the to-do database.
     *
     * @param todo
     * @return 204 (no content)
     */
    @RequestMapping(method = RequestMethod.POST, value = "/")
    public ResponseEntity<Void> postTodo(@RequestBody Todo todo) throws URISyntaxException {

        Todo newTodo = todoRepository.save(todo);
        return ResponseEntity.created(new URI("/todos/" + newTodo.getId())).build();
    }

    /**
     * Delete the to-do with the given id from the to-do database.
     *
     * @param id
     * @return 204 (no content)
     */

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public ResponseEntity<Void> deleteTodoById(@PathVariable String id) {
        todoRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Get the to-do with the given id from the to-do database. Updated all fields (ids, createdDate, lastModifiedDate
     * are excluded) and save it again in the to-do database. If the to-do with the given id doesn't exist, create a new
     * one and save it in the to-do database.
     *
     * @param id
     * @param updatedTodo
     * @return 204 (no content) or 201 (created) if a new to-do was created because it didn't exist.
     */
    @RequestMapping(method = RequestMethod.PUT, value = "/{id}")
    public ResponseEntity<Object> putTodoById(@PathVariable String id, @RequestBody Todo updatedTodo) throws URISyntaxException {
        return todoRepository.findById(id)
                .map(todo -> {
                    todo.setListId(updatedTodo.getListId());
                    todo.setDueDate(updatedTodo.getDueDate());
                    todo.setStatus(updatedTodo.getStatus());
                    todo.setContent(updatedTodo.getContent());
                    todoRepository.save(todo);
                    return ResponseEntity.noContent().build();
                })
                .orElseGet(() -> {

                    try {
                        Todo newTodo = todoRepository.save(updatedTodo);
                        return ResponseEntity.created(new URI("/todos/" + newTodo.getId())).build();
                    } catch (URISyntaxException e) {
                        e.printStackTrace();
                    }
                    return ResponseEntity.status(HttpStatus.CONFLICT).build();
                });
    }


    /**
     * Get all user to-do-lists from the to-do-list database.
     *
     * @param userId
     * @return all to-do-lists from user (200)
     */
    @RequestMapping(method = RequestMethod.GET, value = "/todoLists")
    public List<TodoList> getTodoLists(@RequestHeader("x-uid") String userId) {
        return todoListRepository.findAllByUserId(userId);
    }

    /**
     * Get the user to-do-list by its id, if the to-do-list with the given id doesn't exist, throw an exception.
     *
     * @param userId
     * @param id
     * @return the to-do-list (200)
     */
    @RequestMapping(method = RequestMethod.GET, value = "/todoLists/{id}")
    public TodoList getTodoListById(@RequestHeader("x-uid") String userId, @PathVariable String id) {
        return todoListRepository.findByIdAndUserId(id, userId)
                .orElseThrow(() -> new TodoListNotFoundException(id));
    }

    /**
     * Create a new to-do-list and save it in the to-do-list database.
     *
     * @param todoList
     * @return 201 (created)
     */
    @RequestMapping(method = RequestMethod.POST, value = "/todoLists")
    public ResponseEntity<Void> postTodoList(@RequestBody TodoList todoList) throws URISyntaxException {
        TodoList newTodoList = todoListRepository.save(todoList);
        return ResponseEntity.created(new URI("/todoLists/" + newTodoList.getId())).build();
    }

    /**
     * Delete the to-do-list with the given id from the to-do-list database.
     *
     * @param id
     * @return 204 (no content)
     */
    @RequestMapping(method = RequestMethod.DELETE, value = "/todoLists/{id}")
    public ResponseEntity<Void> deleteTodoListById(@PathVariable String id) {
        todoRepository.deleteByListId(id);
        todoListRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Get the to-do-list with the given id from the to-do-list database. Updated all fields (ids, createdDate, lastModifiedDate
     * are excluded) and save it again in the to-do-list database. If the to-do-list with the given id doesn't exist, create a new
     * one and save it in the to-do-list database.
     *
     * @param id
     * @param updatedTodoList
     * @return 204 (no content) or 201 (created) if a new to-do-list was created because it didn't exist.
     */
    @RequestMapping(method = RequestMethod.PUT, value = "/todoLists/{id}")
    public ResponseEntity<Object> putTodoList(@PathVariable String id, @RequestBody TodoList updatedTodoList) {
        return todoListRepository.findById(id)
                .map(todoList -> {
                    todoList.setName(updatedTodoList.getName());
                    todoListRepository.save(todoList);
                    return ResponseEntity.noContent().build();
                })
                .orElseGet(() -> {
                    try {
                        TodoList newTodoList = todoListRepository.save(updatedTodoList);
                        return ResponseEntity.created(new URI("/todoLists/" + newTodoList.getId())).build();
                    } catch (URISyntaxException e) {
                        e.printStackTrace();
                    }
                    return ResponseEntity.status(HttpStatus.CONFLICT).build();
                });
    }
}

