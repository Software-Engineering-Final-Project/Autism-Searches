package com.articlefetch.app.Controller;

import com.articlefetch.app.Busniess.Service.LoginService;
import com.articlefetch.app.Controller.JacksonModels.Account;
import com.articlefetch.app.Controller.JacksonModels.LoginValidation;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.core.IsNull;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ValidationController.class)
class ValidationControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private LoginService service;

    @Test
    void login() throws Exception {
        LoginValidation validation = new LoginValidation("jschappel", "password");
        Account returnedAccount = new Account("jschappel", "password", "Joshua",
                "Schappel", "j@shu.edu", 12, null, true);

        when(service.validateAccount(validation.getUsername(), validation.getPassword())).thenReturn(returnedAccount);

        mvc.perform(post("/validate/login")
                .contentType(APPLICATION_JSON)
                .content(asJsonString(validation)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$['username']", is("jschappel")))
                .andExpect(jsonPath("$['password']", is("password")))
                .andExpect(jsonPath("$['first_name']", is("Joshua")))
                .andExpect(jsonPath("$['last_name']", is("Schappel")))
                .andExpect(jsonPath("$['email']", is("j@shu.edu")))
                .andExpect(jsonPath("$['id']", is(12)))
                .andExpect(jsonPath("$['path']", IsNull.nullValue()))
                .andExpect(jsonPath("$['status']", is(true)));
    }

    // Helper function that converts a Jackson Object to a json string
    private static String asJsonString(Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}