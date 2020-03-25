package com.articlefetch.app.Busniess.Service;

import com.articlefetch.app.Busniess.Exceptions.AccountNotFoundException;
import com.articlefetch.app.Busniess.Exceptions.InvalidPasswordException;
import com.articlefetch.app.Controller.JacksonModels.Account;
import com.articlefetch.app.Controller.JacksonModels.Authentication;
import com.articlefetch.app.DataAccess.ModelDomain.AccountEntity;
import com.articlefetch.app.DataAccess.Repository.AccountRepository;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
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
    void validateAccount() throws IOException, InvalidKeySpecException, NoSuchAlgorithmException {
        Account returnedAccount = new Account("jschappel", "password", "Joshua",
                "Schappel", "j@shu.edu", 1, null, "/default_user.png", true);

        AccountEntity accountEntry = new AccountEntity()
                .create(null, "Joshua", "Schappel", "jschappel", "password",
                        "j@shu.edu", "/Images/default_user.png", true);

        Authentication validation = new Authentication("jschappel", "password");

        when(repository.findAccountByUserName("jschappel")).thenReturn(Optional.of(accountEntry));

        Account account = loginService.validateAccount(validation.getUsername(), validation.getPassword());

        assertEquals(accountEntry.getFirst_name(), account.getFirst_name());
        assertEquals(accountEntry.getLast_name(), account.getLast_name());
        assertEquals(accountEntry.getUsername(), account.getUsername());
        assertEquals(accountEntry.getPassword(), account.getPassword());
        assertEquals(accountEntry.getEmail(), account.getEmail());
        assertEquals(accountEntry.getStatus(), account.getStatus());
    }


    @Test
    void validateAccount_with_invalid_password() {
        Authentication validation = new Authentication("jschappel", "password");

        when(repository.findAccountByUserName("jschappel")).thenThrow(InvalidPasswordException.class);

        assertThrows(InvalidPasswordException.class, () -> {
            loginService.validateAccount(validation.getUsername(), validation.getPassword());
        });
    }

    @Test
    void validateAccount_with_invalid_username() {
        Authentication validation = new Authentication("jschappel", "password");

        when(repository.findAccountByUserName("jschappel")).thenThrow(AccountNotFoundException.class);

        assertThrows(AccountNotFoundException.class, () -> {
            loginService.validateAccount(validation.getUsername(), validation.getPassword());
        });
    }
}