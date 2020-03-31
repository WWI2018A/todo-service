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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

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

    @SpringBootTest
    public class SmokeTest {

        @Autowired
        private TodoController controller;

        @Test
        public void contextLoads() throws Exception {
            assertThat(controller).isNotNull();
        }
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

        when(todoRepository.findByListIdAndUserId("list-2398rhz43", "user-2348du83ru")).thenReturn(todos);
        List<Todo> result = todoController.getTodos("list-2398rhz43", "user-2348du83ru");
        assertThat(result.size()).isEqualTo(3);
    }

    @Test
    public void getTodoByIdTest() {
        Todo todo = new Todo("user-2348du83ru", "list-2398rhz43", new Date(), "Aufgabe 3");

        String todoId = todo.getId();

        when(todoRepository.findByIdAndUserId(todoId, todo.getUserId())).thenReturn(Optional.of(todo));

        Todo todoResult = todoController.getTodoById(todoId, todo.getUserId());
        verify(todoRepository).findByIdAndUserId(todoId, todo.getUserId());

        assertEquals(todoId, todoResult.getId());
        assertEquals("list-2398rhz43", todoResult.getListId());
        assertEquals("Aufgabe 3", todoResult.getContent());
    }

    @Test
    public void postTodoTest() throws URISyntaxException {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        Todo todo = new Todo("user-2348du83ru", "list-2398rhz43", new Date(), "Aufgabe 1");
        when(todoRepository.save(todo)).thenReturn(todo);
        when(todoRepository.findById(todo.getId())).thenReturn(Optional.of(todo));

        ResponseEntity<Void> responseEntity = todoController.postTodo(todo);
        System.out.println(responseEntity.getStatusCode());
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(201);
        assertThat(todoRepository.findById(todo.getId()).get().getContent()).isEqualTo("Aufgabe 1");
    }

    @Test
    public void deleteTodoByIdTest() {
        Todo todo1 = new Todo("user-2348du83ru", "list-2398rhz43", new Date(), "Aufgabe 1");
        Todo todo2 = new Todo("user-2348du83ru", "list-2398rhz43", new Date(), "Aufgabe 2");
        Todo todo3 = new Todo("user-2348du83ru", "list-2398rhz43", new Date(), "Aufgabe 3");

        List<Todo> todos = new ArrayList<>();
        todos.add(todo1);
        todos.add(todo2);
        todos.add(todo3);

        when(todoRepository.findAll()).thenReturn(todos);
        doAnswer(invocation -> {
            todos.remove(todo1);
            return null;
        }).when(todoRepository).deleteById(todo1.getId());

        ResponseEntity<Void> responseEntity = todoController.deleteTodoById(todo1.getId());

        assertEquals(204, responseEntity.getStatusCodeValue());
        assertEquals(todoRepository.findAll().size(), 2);
    }

    @Test
    public void putTodoByIdTest() throws URISyntaxException {
        Todo todo1 = new Todo("user-2348du83ru", "list-2398rhz43", new Date(), "Aufgabe 1");
        String todo1Id = todo1.getId();

        when(todoRepository.findById(todo1Id)).thenReturn(Optional.of(todo1));
        when(todoRepository.save(todo1)).thenReturn(todo1);

        ResponseEntity<Object> responseEntity = todoController.putTodoById(todo1Id,
                new Todo(todo1.getUserId(), todo1.getListId(), todo1.getDueDate(), "Neue Aufgabe"));

        assertEquals(responseEntity.getStatusCodeValue(), 204);
        assertEquals(todoRepository.findById(todo1Id).get().getContent(), "Neue Aufgabe");
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

        when(todoListRepository.findAllByUserId("user-2348du83rx")).thenReturn(todoLists);

        List<TodoList> result = todoController.getTodoLists("user-2348du83rx");
        assertThat(result.size()).isEqualTo(3);
    }

    @Test
    public void getTodoListsbyIdTest() {
        TodoList todoList3 = new TodoList("user-2348du83rx", "Einkaufsliste 3");
        String todoList3Id = todoList3.getId();

        when(todoListRepository.findByIdAndUserId(todoList3Id, "user-2348du83rx")).thenReturn(Optional.of(todoList3));

        TodoList todoLists = todoController.getTodoListById("user-2348du83rx", todoList3Id);
        assertEquals(todoList3Id, todoLists.getId());
    }

    @Test
    public void postTodoListsTest() throws URISyntaxException {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        TodoList todoList1 = new TodoList("user-2348du83rx", "Einkaufsliste 1");

        when(todoListRepository.save(todoList1)).thenReturn(todoList1);
        when(todoListRepository.findByIdAndUserId(todoList1.getId(), todoList1.getUserId())).thenReturn(Optional.of(todoList1));

        ResponseEntity<Void> responseEntity = todoController.postTodoList(todoList1);
        assertEquals(201, responseEntity.getStatusCodeValue());
        assertEquals("Einkaufsliste 1", todoListRepository.
                findByIdAndUserId(todoList1.getId(), todoList1.getUserId()).get().getName());
    }

    @Test
    public void deleteTodoListsByIdTest() {
        String userId = "user-2348du83rx";
        TodoList todoList1 = new TodoList(userId, "Einkaufsliste 1");
        TodoList todoList2 = new TodoList(userId, "Einkaufsliste 2");
        TodoList todoList3 = new TodoList(userId, "Einkaufsliste 3");

        List<TodoList> todoLists = new ArrayList<>();
        todoLists.add(todoList1);
        todoLists.add(todoList2);
        todoLists.add(todoList3);

        when(todoListRepository.findAllByUserId(userId)).thenReturn(todoLists);
        doAnswer(invocation -> {
            todoLists.remove(todoList1);
            return null;
        }).when(todoListRepository).deleteById(todoList1.getId());

        ResponseEntity<Void> responseEntity = todoController.deleteTodoListById(todoList1.getId());

        assertEquals(204, responseEntity.getStatusCodeValue());
        assertEquals(2, todoListRepository.findAllByUserId(userId).size());
    }


    @Test
    public void putTodoListsByIdTest() throws URISyntaxException {
        TodoList todoList1 = new TodoList("user-2348du83rx", "neue Einkaufsliste");
        String todoList1Id = todoList1.getId();

        when(todoListRepository.findById(todoList1Id)).thenReturn(Optional.of(todoList1));
        when(todoListRepository.save(todoList1)).thenReturn(todoList1);

        ResponseEntity<Object> responseEntity = todoController.putTodoList(todoList1Id,
                new TodoList(todoList1.getUserId(), todoList1.getName()));

        assertEquals(204, responseEntity.getStatusCodeValue());
        assertEquals("neue Einkaufsliste", todoListRepository.findById(todoList1Id).get().getName());
    }
}