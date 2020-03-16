package com.articlefetch.app.Busniess.Service;

import com.articlefetch.app.Busniess.Exceptions.AccountNotFoundException;
import com.articlefetch.app.Busniess.Exceptions.InvalidPasswordException;
import com.articlefetch.app.Controller.JacksonModels.Account;
import com.articlefetch.app.Controller.JacksonModels.LoginValidation;
import com.articlefetch.app.DataAccess.ModelDomain.AccountEntity;
import com.articlefetch.app.DataAccess.Repository.AccountRepository;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class LoginServiceImplTest {

    @InjectMocks
    LoginServiceImpl loginService;

    @Mock
    AccountRepository repository;

    @BeforeClass
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void validateAccount() {
        Account returnedAccount = new Account("jschappel", "password", "Joshua",
                "Schappel", "j@shu.edu", null, null, true);

        AccountEntity accountEntry = new AccountEntity()
                .create(null, "Joshua", "Schappel", "jschappel", "password",
                        "j@shu.edu", true);

        LoginValidation validation = new LoginValidation("jschappel", "password");

        when(repository.findAccountByUserName("jschappel")).thenReturn(Optional.of(accountEntry));

        Account account = loginService.validateAccount(validation.getUsername(), validation.getPassword());

        assertEquals(accountEntry.getFirst_name(), account.first_name);
        assertEquals(accountEntry.getLast_name(), account.last_name);
        assertEquals(accountEntry.getUsername(), account.username);
        assertEquals(accountEntry.getPassword(), account.password);
        assertEquals(accountEntry.getEmail(), account.email);
        assertEquals(accountEntry.getStatus(), account.status);
    }


    @Test
    void validateAccount_with_invalid_password() {
        LoginValidation validation = new LoginValidation("jschappel", "password");

        when(repository.findAccountByUserName("jschappel")).thenThrow(InvalidPasswordException.class);

        assertThrows(InvalidPasswordException.class, () -> {
            loginService.validateAccount(validation.getUsername(), validation.getPassword());
        });
    }

    @Test
    void validateAccount_with_invalid_username() {
        LoginValidation validation = new LoginValidation("jschappel", "password");

        when(repository.findAccountByUserName("jschappel")).thenThrow(AccountNotFoundException.class);

        assertThrows(AccountNotFoundException.class, () -> {
            loginService.validateAccount(validation.getUsername(), validation.getPassword());
        });
    }
}