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

    TodoController(TodoRepository todoRepository, TodoListRepository todoListRepository) {
        this.todoRepository = todoRepository;
        this.todoListRepository = todoListRepository;
    }


    /**
     * Get all to dos from the to do database
     *
     * @return list of all to dos.
     */
    @RequestMapping(method = RequestMethod.GET, value = "/")
    public List<Todo> getTodos(@RequestParam(required = false) String listId) {
        if (listId != null) {
            return todoRepository.findByListId(listId);
        }
        return todoRepository.findAll();
    }

    /**
     * Get the to do with the given id from the to do database. If no to do could be found, throw an Exception.
     *
     * @param id
     * @return to do with the given id
     */
    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public Todo getTodoById(@PathVariable String id) {
        return todoRepository.findById(id)
                .orElseThrow(() -> new TodoNotFoundException(id));
    }

    /**
     * Create a new to do and save it to the to do database.
     *
     * @param todo
     * @return new created to do
     */
    @RequestMapping(method = RequestMethod.POST, value = "/")
    public ResponseEntity<Void> postTodo(@RequestBody Todo todo) throws URISyntaxException {

        Todo newTodo = todoRepository.save(todo);
        return ResponseEntity.created(new URI("/todos/" + newTodo.getId())).build();
    }

    /**
     * Delete the to do with the given id from the to do database.
     *
     * @param id
     * @return
     */

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public ResponseEntity<Void> deleteTodoById(@PathVariable String id) {
        todoRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Get the to do with the given id from the to do database. Updated all fields (ids, createdDate, lastModifiedDate
     * etc. are excluded) and save it again in the to do database. If no to do exists with the given id, create a new
     * one and save it to the to do database.
     *
     * @param id
     * @param updatedTodo
     * @return the updated to do with the given id (or the new to do)
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
     * Get list of al to do lists of the to do list database
     *
     * @return list of all to do lists
     */
    @RequestMapping(method = RequestMethod.GET, value = "/todoLists")
    public List<TodoList> getTodoLists() {
        return todoListRepository.findAll();
    }

    /**
     * @param id
     * @return to do list with the given id.
     * <p>
     * If no to do list exists with the given id, throw an exception.
     */
    @RequestMapping(method = RequestMethod.GET, value = "/todoLists/{id}")
    public TodoList getTodoListById(@PathVariable String id) {
        return todoListRepository.findById(id)
                .orElseThrow(() -> new TodoListNotFoundException(id));
    }

    /**
     * Return a new to do list and save it to the to do list database.
     *
     * @param todoList
     * @return new created to do list
     */
    @RequestMapping(method = RequestMethod.POST, value = "/todoLists")
    public ResponseEntity<Void> postTodoList(@RequestBody TodoList todoList) throws URISyntaxException {
        TodoList newTodoList = todoListRepository.save(todoList);
        return ResponseEntity.created(new URI("/todoLists/" + newTodoList.getId())).build();
    }

    /**
     * Delete the to do list with the given id from the to do list database.
     *
     * @param id
     * @return
     */
    @RequestMapping(method = RequestMethod.DELETE, value = "/todoLists/{id}")
    public ResponseEntity<Void> deleteTodoList(@PathVariable String id) {
        // TODO: Delete all to dos with the given to do list id
        todoRepository.findByListId(id);
        todoRepository.deleteById(id);
        todoListRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * @param id
     * @param updatedTodoList
     * @return
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

