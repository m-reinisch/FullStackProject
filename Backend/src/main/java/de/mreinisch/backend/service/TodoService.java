package de.mreinisch.backend.service;

import de.mreinisch.backend.dto.TodoDTO;
import de.mreinisch.backend.model.Todo;
import de.mreinisch.backend.repository.TodoRepo;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TodoService {
    private final TodoRepo repo;
    private final IdService idService;

    public TodoService(TodoRepo repo, IdService idService) {
        this.repo = repo;
        this.idService = idService;
    }

    /** Reads the todos from the database.
     *
     * @return list of todos
     */
    public List<Todo> readTodos(){
        return repo.findAll();
    }

    /** Creates a To-Do and saves it to the database.
     *
     * @param todoDTO to be stored
     * @return stored To-Do
     */
    public TodoDTO generateTodo(TodoDTO todoDTO){
        String id= idService.generateId();
        Todo todo= new Todo(id,
                todoDTO.description(),
                todoDTO.status());

        repo.save(todo);
        return todoDTO;
    }

    /** Writes the changed data to the database.
     *
     * @param newTodo to change
     * @return saved To-Do
     */
    public Todo updateTodo(Todo newTodo){
        Todo todo= repo.findById(newTodo.id()).orElse(null);

        if (todo == null) {
            return todo;
        } else {
            repo.save(todo
                    .withDescription(newTodo.description())
                    .withStatus(newTodo.status()));
            return newTodo;
        }
    }

    /** Delete the To-Do from the database.
     *
     * @param id of To-Do to delete
     */
    public void removeTodo(String id){
        repo.deleteById(id);
    }

    /** Searches the database for a To-Do with a specific ID.
     *
     * @param id to search
     * @return To-Do
     */
    public Todo findTodoById(String id) {
        return repo.findById(id).orElse(null);
    }
}
