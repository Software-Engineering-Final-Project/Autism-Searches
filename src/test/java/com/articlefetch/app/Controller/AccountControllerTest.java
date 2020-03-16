package com.articlefetch.app.Controller;

import com.articlefetch.app.Busniess.Service.AccountService;
import com.articlefetch.app.Controller.JacksonModels.Account;
import org.hamcrest.core.IsNull;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;


import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.Is.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

@WebMvcTest(AccountController.class)
class AccountControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private AccountService service;

    @Test
    void getAllAccounts() throws Exception {
        Account a1 = new Account("jschappel", "password", "Joshua",
                "Schappel", "j@shu.edu", null, null, true);

        List<Account> accountList = Arrays.asList(a1);

        given(service.getAllAccounts()).willReturn(accountList);

        mvc.perform(get("/account/allAccounts")
                .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].username", is("jschappel")))
                .andExpect(jsonPath("$[0].password", is("password")))
                .andExpect(jsonPath("$[0].first_name", is("Joshua")))
                .andExpect(jsonPath("$[0].last_name", is("Schappel")))
                .andExpect(jsonPath("$[0].email", is("j@shu.edu")))
                .andExpect(jsonPath("$[0].id", IsNull.nullValue()))
                .andExpect(jsonPath("$[0].path", IsNull.nullValue()))
                .andExpect(jsonPath("$[0].status", is(true)));
    }

    @Test
    void getAccount() throws Exception {
        Account a1 = new Account("jschappel", "password", "Joshua",
                "Schappel", "j@shu.edu", 1, null, true);

        given(service.getAccount(1)).willReturn(a1);

        mvc.perform(get("/account/getAccount?id=1")
                .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].username", is("jschappel")))
                .andExpect(jsonPath("$[0].password", is("password")))
                .andExpect(jsonPath("$[0].first_name", is("Joshua")))
                .andExpect(jsonPath("$[0].last_name", is("Schappel")))
                .andExpect(jsonPath("$[0].email", is("j@shu.edu")))
                .andExpect(jsonPath("$[0].id", is("1")))
                .andExpect(jsonPath("$[0].path", IsNull.nullValue()))
                .andExpect(jsonPath("$[0].status", is(true)));
    }

    @Test
    void getAccount_without_params() throws Exception {
        mvc.perform(get("/account/getAccount")
                .contentType(APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message", is("id parameter is missing")));
    }

    @Test
    void createAccount() {
        Account a1 = new Account("jschappel", "password", "Joshua",
                "Schappel", "j@shu.edu", 1, null, true);
    }

    @Test
    void reactivateAccount() {
    }

    @Test
    void deactivateAccount() {
    }
}