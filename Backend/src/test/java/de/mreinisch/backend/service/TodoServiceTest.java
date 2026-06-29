package de.mreinisch.backend.service;

import de.mreinisch.backend.dto.TodoDTO;
import de.mreinisch.backend.model.Todo;
import de.mreinisch.backend.repository.TodoRepo;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

class TodoServiceTest {

    @Test
    void readTodos_shouldReturnEmptyList_whenDatabaseEmpty() {
        TodoRepo mockingRepro= mock(TodoRepo.class);
        IdService mockingIdService= mock(IdService.class);
        TodoService service= new TodoService(mockingRepro, mockingIdService);
        List<Todo> expected= Collections.emptyList();
        List<Todo> actual;

        actual= service.readTodos();
        assertEquals(expected, actual);
    }

    @Test
    void readTodos_shouldReturnList_whenDatabaseNotEmpty() {
        TodoRepo mockingRepro= mock(TodoRepo.class);
        IdService mockingIdService= mock(IdService.class);
        TodoService service= new TodoService(mockingRepro, mockingIdService);
        Todo todo= new Todo("1", "Testing", "OPEN");
        List<Todo> expected= new ArrayList<>(List.of(todo));
        List<Todo> actual;

        when(mockingRepro.findAll()).thenReturn(new ArrayList<>(List.of(todo)));
        actual= service.readTodos();
        assertEquals(expected, actual);
    }

    @Test
    void generateTodo_shouldReturnTodo_whenSaved() {
        TodoRepo mockingRepro= mock(TodoRepo.class);
        IdService mockingIdService= mock(IdService.class);
        TodoService service= new TodoService(mockingRepro, mockingIdService);
        String id= "1";
        Todo todo= new Todo(id, "Testing", "OPEN");
        TodoDTO expected= new TodoDTO("Testing", "OPEN");
        TodoDTO actual;

        when(mockingIdService.generateId()).thenReturn(id);
        when(mockingRepro.save(todo)).thenReturn(todo);
        actual= service.generateTodo(expected);
        assertEquals(expected, actual);
        verify(mockingRepro, times(1)).save(todo);
        verifyNoMoreInteractions(mockingRepro);
    }

    @Test
    void updateTodo_shouldReturnNULL_whenNotFoundInDatabase() {
        TodoRepo mockingRepro= mock(TodoRepo.class);
        IdService mockingIdService= mock(IdService.class);
        TodoService service= new TodoService(mockingRepro, mockingIdService);
        String id= "1";
        Todo todo= new Todo(id, "Testing", "IN_PROGRESS");
        Todo expected= null;
        Todo actual;

        when(mockingRepro.findById(id)).thenReturn(Optional.empty());
        actual= service.updateTodo(todo);
        assertEquals(expected, actual);
        verify(mockingRepro, times(0)).save(todo);
//        verifyNoMoreInteractions(mockingRepro);
    }

    @Test
    void updateTodo_shouldReturnUpdatedTodo_whenFoundInDatabase() {
        TodoRepo mockingRepro= mock(TodoRepo.class);
        IdService mockingIdService= mock(IdService.class);
        TodoService service= new TodoService(mockingRepro, mockingIdService);
        String id= "1";
        Todo todo= new Todo(id, "Testing", "IN_PROGRESS");
        Todo expected= todo;
        Todo actual;

        when(mockingRepro.findById(id)).thenReturn(Optional.of(todo));
        actual= service.updateTodo(todo);
        assertEquals(expected, actual);
        verify(mockingRepro, times(1)).save(todo);
//        verifyNoMoreInteractions(mockingRepro);
    }

    @Test
    void removeTodo(){
        TodoRepo mockingRepro= mock(TodoRepo.class);
        IdService mockingIdService= mock(IdService.class);
        TodoService service= new TodoService(mockingRepro, mockingIdService);
        String id= "1";

        service.removeTodo(id);
        verify(mockingRepro).deleteById(id);
        verifyNoMoreInteractions(mockingRepro);
    }

    @Test
    void findTodoById_shouldReturnNULL_whenNotFoundInDatabase() {
        TodoRepo mockingRepro= mock(TodoRepo.class);
        IdService mockingIdService= mock(IdService.class);
        TodoService service= new TodoService(mockingRepro, mockingIdService);
        String id= "1";
        Todo expected= null;
        Todo actual;

        when(mockingRepro.findById(id)).thenReturn(Optional.empty());
        actual= service.findTodoById(id);
        assertEquals(expected, actual);
        verify(mockingRepro, times(1)).findById(id);
        verifyNoMoreInteractions(mockingRepro);
    }

    @Test
    void findTodoById_shouldReturnTodo_whenFoundInDatabase() {
        TodoRepo mockingRepro= mock(TodoRepo.class);
        IdService mockingIdService= mock(IdService.class);
        TodoService service= new TodoService(mockingRepro, mockingIdService);
        String id= "1";
        Todo todo= new Todo(id, "Testing", "IN_PROGRESS");
        Todo expected= todo;
        Todo actual;

        when(mockingRepro.findById(id)).thenReturn(Optional.of(todo));
        actual= service.findTodoById(id);
        assertEquals(expected, actual);
        verify(mockingRepro, times(1)).findById(id);
        verifyNoMoreInteractions(mockingRepro);
    }
}