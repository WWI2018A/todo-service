package com.dhbw.todoservice.controller;

import com.dhbw.todoservice.exceptions.toDoListNotFound.TodoListNotFoundException;
import com.dhbw.todoservice.exceptions.toDoNotFound.TodoNotFoundException;
import com.dhbw.todoservice.models.Todo;
import com.dhbw.todoservice.models.TodoList;
import com.dhbw.todoservice.repositories.TodoListRepository;
import com.dhbw.todoservice.repositories.TodoRepository;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
     * @param newTodo
     * @return new created to do
     */
    @RequestMapping(method = RequestMethod.POST, value = "/")
    public Todo postTodo(@RequestBody Todo newTodo) {
        return todoRepository.save(newTodo);
    }

    /**
     * Delete the to do with the given id from the to do database.
     *
     * @param id
     */
    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public void deleteTodoById(@PathVariable String id) {
        todoRepository.deleteById(id);
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
    public Todo putTodoById(@PathVariable String id, @RequestBody Todo updatedTodo) {
        return todoRepository.findById(id)
                .map(todo -> {
                    todo.setListId(updatedTodo.getListId());
                    todo.setDueDate(updatedTodo.getDueDate());
                    todo.setStatus(updatedTodo.getStatus());
                    todo.setContent(updatedTodo.getContent());
                    return todoRepository.save(todo);
                })
                .orElseGet(() -> todoRepository.save(updatedTodo));
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
     * @param newTodoList
     * @return new created to do list
     */
    @RequestMapping(method = RequestMethod.POST, value = "/todoLists")
    public TodoList postTodoList(@RequestBody TodoList newTodoList) {
        return todoListRepository.save(newTodoList);
    }

    /**
     * Delete the to do list with the given id from the to do list database.
     *
     * @param id
     */
    @RequestMapping(method = RequestMethod.DELETE, value = "/todoLists/{id}")
    public void deleteTodoList(@PathVariable String id) {
        // TODO: Delete all to dos with the given to do list id
        todoListRepository.deleteById(id);
    }

    /**
     * @param id
     * @param updatedTodoList
     * @return
     */
    @RequestMapping(method = RequestMethod.PUT, value = "/todoLists/{id}")
    public TodoList putTodoList(@PathVariable String id, @RequestBody TodoList updatedTodoList) {
        return todoListRepository.findById(id)
                .map(todoList -> {
                    todoList.setName(updatedTodoList.getName());
                    return todoListRepository.save(todoList);
                })
                .orElseGet(() -> todoListRepository.save(updatedTodoList));
    }

}
