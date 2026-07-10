package de.mreinisch.backend.controller;

import de.mreinisch.backend.dto.TodoDTO;
import de.mreinisch.backend.model.Todo;
import de.mreinisch.backend.repository.TodoRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import tools.jackson.databind.ObjectMapper;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode= DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class TodoControllerTest {
    @Autowired
    private MockMvc mvc;
    @Autowired
    private TodoRepo repo;

    @Test
    @WithMockUser
    void getAllTodos_shouldReturnEmptyJson_whenInitiallyStarted() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/api/todo"))
                .andExpect(status().isOk())
                .andExpect(content().json("[]"));
    }

    @Test
    @WithMockUser
    void getAllTodos_shouldReturnTestingTodo_whenCalled() throws Exception {
        Todo todo= new Todo("1", "Testing", "OPEN");
        ObjectMapper mapper= new ObjectMapper();
        String jsonTodo= "[" + mapper.writeValueAsString(todo) + "]";

        repo.save(todo);
        mvc.perform(MockMvcRequestBuilders.get("/api/todo"))
                .andExpect(status().isOk())
                .andExpect(content().json(jsonTodo));
    }

    @Test
    @WithMockUser
    void putTodo_shouldReturnTestingTodo_whenCalledWithTestingTodo() throws Exception {
        Todo todo= new Todo("1", "Testing", "OPEN");
        ObjectMapper mapper= new ObjectMapper();
        String jsonTodo= mapper.writeValueAsString(new TodoDTO("Testing", "OPEN"));

        repo.save(todo);
        mvc.perform(MockMvcRequestBuilders.post("/api/todo")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonTodo))
                .andExpect(status().isOk())
                .andExpect(content().json(jsonTodo));
    }

    @Test
    @WithMockUser
    void updateTodo_shouldReturnUpdatedTodo_whenCalledWithNewTodo() throws Exception {
        Todo todo= new Todo("1", "Testing", "OPEN");
        Todo newtodo= new Todo("1", "Testing", "IN_PROGRESS");
        ObjectMapper mapper= new ObjectMapper();
        String expected= mapper.writeValueAsString(newtodo);

        repo.save(todo);
        mvc.perform(MockMvcRequestBuilders.put("/api/todo/" + newtodo.id())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(expected))
                .andExpect(status().isOk())
                .andExpect(content().json(expected));
    }

    @Test
    @WithMockUser
    void deleteTodo() throws Exception {
        Todo todo= new Todo("1", "Testing", "DONE");

        repo.save(todo);
        mvc.perform(MockMvcRequestBuilders.delete("/api/todo/1"))
                .andExpect(status().isOk());
        assertThat(repo.findById("1").isEmpty());
    }

    @Test
    @WithMockUser
    void getTodoById_shouldReturnTodo_whenCalledWithCorrectId() throws Exception {
        Todo todo= new Todo("1", "Testing", "OPEN");
        ObjectMapper mapper= new ObjectMapper();
        String expected= mapper.writeValueAsString(todo);

        repo.save(todo);
        mvc.perform(MockMvcRequestBuilders.get("/api/todo/1"))
                .andExpect(status().isOk())
                .andExpect(content().json(expected));
    }
}