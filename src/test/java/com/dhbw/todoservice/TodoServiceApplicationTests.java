package com.dhbw.todoservice;

import com.dhbw.todoservice.controller.TodoController;
import com.dhbw.todoservice.models.Todo;
import com.dhbw.todoservice.repositories.TodoListRepository;
import com.dhbw.todoservice.repositories.TodoRepository;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.event.annotation.PrepareTestInstance;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
class TodoServiceApplicationTests {

    @BeforeEach
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
        todoController = new TodoController(todoRepository, todoListRepository);
    }

    @Mock
    TodoRepository todoRepository;

    @Mock
    TodoListRepository todoListRepository;

    TodoController todoController;

    @Test
    void contextLoads() {
    }

    @Test
    public void getTodos() {
        Todo todo1 = new Todo("user-2348du83ru", "list-2398rhz43", new Date(), "Aufgabe 1");
        Todo todo2 = new Todo("user-2348du83ru", "list-2398rhz43", new Date(), "Aufgabe 2");
        Todo todo3 = new Todo("user-2348du83ru", "list-2398rhz43", new Date(), "Aufgabe 3");

        List<Todo> todos = new ArrayList<>();
        todos.add(todo1);
        todos.add(todo2);
        todos.add(todo3);

        when(todoRepository.findByListId("list-2398rhz43")).thenReturn(todos);

        List<Todo> result = todoController.getTodos("list-2398rhz43");
        assertThat(result.size()).isEqualTo(3);
    }
}
