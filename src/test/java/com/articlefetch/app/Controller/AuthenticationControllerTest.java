package com.articlefetch.app.Controller;

import com.articlefetch.app.Busniess.Exceptions.AccountNotFoundException;
import com.articlefetch.app.Busniess.Exceptions.InvalidPasswordException;
import com.articlefetch.app.Busniess.Service.LoginService;
import com.articlefetch.app.Controller.JacksonModels.Account;
import com.articlefetch.app.Controller.JacksonModels.AccountCreate;
import com.articlefetch.app.Controller.JacksonModels.Authentication;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.core.IsNull;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AuthenticationController.class)
class AuthenticationControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private LoginService service;

    @Test
    void login() throws Exception {
        Authentication validation = new Authentication("jschappel", "password");
        Account returnedAccountCreate = new Account("jschappel",  "Joshua",
                "Schappel", "j@shu.edu", 12, null, "/default_user.png", true);

        when(service.validateAccount(validation.getUsername(), validation.getPassword())).thenReturn(returnedAccountCreate);

        mvc.perform(post("/authenticate/login")
                .contentType(APPLICATION_JSON)
                .content(asJsonString(validation)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$['username']", is("jschappel")))
                .andExpect(jsonPath("$['first_name']", is("Joshua")))
                .andExpect(jsonPath("$['last_name']", is("Schappel")))
                .andExpect(jsonPath("$['email']", is("j@shu.edu")))
                .andExpect(jsonPath("$['id']", is(12)))
                .andExpect(jsonPath("$['status']", is(true)));
    }

    @Test
    void login_with_invalid_password() throws Exception {
        Authentication validation = new Authentication("jschappel", "password2");

        when(service.validateAccount(validation.getUsername(), validation.getPassword()))
                .thenThrow(new InvalidPasswordException(validation.getUsername()));

        mvc.perform(post("/authenticate/login")
                .contentType(APPLICATION_JSON)
                .content(asJsonString(validation)))
                .andExpect(jsonPath("$['message']", is("Invalid password for jschappel")));
    }

    @Test
    void login_with_invalid_username() throws Exception {
        Authentication validation = new Authentication("jschappel", "password2");

        when(service.validateAccount(validation.getUsername(), validation.getPassword()))
                .thenThrow(new AccountNotFoundException(validation.getUsername()));

        mvc.perform(post("/authenticate/login")
                .contentType(APPLICATION_JSON)
                .content(asJsonString(validation)))
                .andExpect(jsonPath("$['message']", is("Username: jschappel does not exist")));
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