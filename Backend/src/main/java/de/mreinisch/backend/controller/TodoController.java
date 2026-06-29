package de.mreinisch.backend.controller;

import de.mreinisch.backend.dto.TodoDTO;
import de.mreinisch.backend.model.Todo;
import de.mreinisch.backend.service.TodoService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("api/todo")
public class TodoController {
    private final TodoService service;

    public TodoController(TodoService service) {
        this.service = service;
    }

    @GetMapping
    public List<Todo> getAllTodos(){
        return service.readTodos();
    }

    @GetMapping("{id}")
    public Todo getTodoById(@PathVariable String id){
        return service.findTodoById(id);
    }

    @PostMapping
    public TodoDTO putTodo(@RequestBody TodoDTO todo){
        return service.generateTodo(todo);
    }

    @PutMapping("{id}")
    public Todo updateTodo(@RequestBody Todo todo){
        return service.updateTodo(todo);
    }

    @DeleteMapping("{id}")
    public void deleteTodo(@PathVariable String id){
        service.removeTodo(id);
    }
}
