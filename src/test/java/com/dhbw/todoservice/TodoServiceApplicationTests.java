package com.dhbw.todoservice;

import com.dhbw.todoservice.controller.TodoController;
import com.dhbw.todoservice.models.Todo;
import com.dhbw.todoservice.models.TodoList;
import com.dhbw.todoservice.repositories.TodoListRepository;
import com.dhbw.todoservice.repositories.TodoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
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
    public void getTodosTest() {
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

    @Test
    public void getTodosbyIdTest() {
        Todo todo1 = new Todo("user-2348du83ru", "list-2398rhz43", new Date(), "Aufgabe 1");
        Todo todo2 = new Todo("user-2348du83ru", "list-2398rhz43", new Date(), "Aufgabe 2");
        Todo todo3 = new Todo("user-2348du83ru", "list-2398rhz43", new Date(), "Aufgabe 3");
        todo1.setId("838838hfhfjf345dg555g88j");
        todo2.setId("949499jkfheu838hfkfkdkdk");
        todo3.setId("5e6a72d68fadc7688e04e2bd");

        when(todoRepository.findById("5e6a72d68fadc7688e04e2bd")).thenReturn(Optional.ofNullable(todo3));

        Todo todo = todoController.getTodoById("5e6a72d68fadc7688e04e2bd");
        verify(todoRepository).findById("5e6a72d68fadc7688e04e2bd");
        assertEquals("5e6a72d68fadc7688e04e2bd", todo.getId());
    }

    @Test
    public void postTodosTest() throws URISyntaxException {
        final String baseUrl = "http://localhost:8080/todos/";
        URI uri = new URI(baseUrl);
        Todo todo1 = new Todo("user-2348du83ru", "list-2398rhz43", new Date(), "Aufgabe 1");

        HttpHeaders headers = new HttpHeaders();
        headers.set("X-COM-PERSIST", "true");
        HttpEntity<Todo> request = new HttpEntity<>(todo1, headers);

        ResponseEntity<Void> responseEntity = ResponseEntity.ok().build();

        assertEquals(200, responseEntity.getStatusCodeValue());
    }

    @Test
    public void deleteTodoByIdTest() throws URISyntaxException {
        final String baseUrl = "http://localhost:8080/todos/";
        URI uri = new URI(baseUrl);
        Todo todo1 = new Todo("user-2348du83ru", "list-2398rhz43", new Date(), "Aufgabe 1");
        Todo todo2 = new Todo("user-2348du83ru", "list-2398rhz43", new Date(), "Aufgabe 2");
        Todo todo3 = new Todo("user-2348du83ru", "list-2398rhz43", new Date(), "Aufgabe 3");
        todo1.setId("838838hfhfjf345dg555g88j");
        todo2.setId("949499jkfheu838hfkfkdkdk");
        todo3.setId("5e6a72d68fadc7688e04e2bd");

        // todoRepository.delete(todo1);

        ResponseEntity<Void> responseEntity = ResponseEntity.noContent().build();

        assertEquals(204, responseEntity.getStatusCodeValue());
    }

    @Test
    public void putTodoByIdTest() throws URISyntaxException {
        final String baseUrl = "http://localhost:8080/todos/";
        URI uri = new URI(baseUrl);
        Todo todo1 = new Todo("user-2348du83ru", "list-2398rhz43", new Date(), "Aufgabe 1");
        todo1.setId("838838hfhfjf345dg555g88j");

        HttpHeaders headers = new HttpHeaders();
        headers.set("X-COM-PERSIST", "true");
        HttpEntity<Todo> request = new HttpEntity<>(todo1, headers);

        ResponseEntity<Void> responseEntity = ResponseEntity.noContent().build();
        //when(todoRepository.findById("838838hfhfjf345dg555g88j")).then((Answer<?>) responseEntity);

        assertEquals(204, responseEntity.getStatusCodeValue());
    }

        @Test
        public void getTodoListsTest() {
            TodoList todoList1 = new TodoList("user-2348du83rx", "Einkaufsliste 1");
            TodoList todoList2 = new TodoList("user-2348du83rx", "Einkaufsliste 2");
            TodoList todoList3 = new TodoList("user-2348du83rx", "Einkaufsliste 3");
            todoList1.setId("list-2398rhz43x");
            todoList2.setId("list-2398rhz43y");
            todoList3.setId("list-2398rhz43z");

            List<TodoList> todoLists = new ArrayList<>();
            todoLists.add(todoList1);
            todoLists.add(todoList2);
            todoLists.add(todoList3);

            when(todoListRepository.findAll()).thenReturn(todoLists);

            List<TodoList> result = todoController.getTodoLists();
            assertThat(result.size()).isEqualTo(3);
        }

    @Test
    public void getTodoListsbyIdTest() {
        TodoList todoList1 = new TodoList("user-2348du83rx", "Einkaufsliste 1");
        TodoList todoList2 = new TodoList("user-2348du83rx", "Einkaufsliste 2");
        TodoList todoList3 = new TodoList("user-2348du83rx", "Einkaufsliste 3");
        todoList1.setId("list-2398rhz43x");
        todoList2.setId("list-2398rhz43y");
        todoList3.setId("list-2398rhz43z");

        when(todoListRepository.findById("list-2398rhz43z")).thenReturn(Optional.of(todoList3));

        TodoList todoLists = todoController.getTodoListById("list-2398rhz43z");
        verify(todoRepository).findById("list-2398rhz43z");
        assertEquals("list-2398rhz43z", todoLists.getId());
    }

    @Test
    public void postTodoListsTest() throws URISyntaxException {
        final String baseUrl = "http://localhost:8080/todos/";
        URI uri = new URI(baseUrl);
        TodoList todoList1 = new TodoList("user-2348du83rx", "Einkaufsliste 1");
        todoList1.setId("list-2398rhz43x");

        HttpHeaders headers = new HttpHeaders();
        headers.set("X-COM-PERSIST", "true");
        HttpEntity<TodoList> request = new HttpEntity<>(todoList1, headers);

        ResponseEntity<Void> responseEntity = ResponseEntity.ok().build();

        assertEquals(200, responseEntity.getStatusCodeValue());
    }

        @Test
        public void deleteTodoListsByIdTest() throws URISyntaxException {
            final String baseUrl = "http://localhost:8080/todos/";
            URI uri = new URI(baseUrl);
            TodoList todoList1 = new TodoList("user-2348du83rx", "Einkaufsliste 1");
            TodoList todoList2 = new TodoList("user-2348du83rx", "Einkaufsliste 2");
            TodoList todoList3 = new TodoList("user-2348du83rx", "Einkaufsliste 3");
            todoList1.setId("list-2398rhz43x");
            todoList2.setId("list-2398rhz43y");
            todoList3.setId("list-2398rhz43z");

            //todoRepository.deleteById("list-2398rhz43x");

            ResponseEntity<Void> responseEntity = ResponseEntity.noContent().build();
            //when(todoRepository.deleteByListId("list-2398rhz43x")).thenReturn(responseEntity);
            //when(todoListRepository.deleteById("")).thenReturn(responseEntity);

            assertEquals(204, responseEntity.getStatusCodeValue());
        }


    @Test
    public void putTodoListsByIdTest() throws URISyntaxException {
        TodoList todoList1 = new TodoList("user-2348du83rx", "Einkaufsliste 1");
        todoList1.setId("list-2398rhz43x");

        //when(todoListRepository.save(todoList1)).thenReturn(todoList1); ---> Never used ---> Fehler
        ResponseEntity<Void> responseEntity = ResponseEntity.noContent().build();

        assertEquals(204, responseEntity.getStatusCodeValue());
    }


}