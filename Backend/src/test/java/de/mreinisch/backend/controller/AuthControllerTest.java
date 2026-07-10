package de.mreinisch.backend.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.oidcLogin;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
class AuthControllerTest {
    @Autowired
    private MockMvc mvc;

    @Test
    @DirtiesContext
    void getMe() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/api/auth/me")
                    .with(oidcLogin()
                            .userInfoToken(t -> t.claim("login", "Max"))))
            .andExpect(status().isOk())
            .andExpect(content().string("Max"));
    }
}