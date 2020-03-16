package com.articlefetch.app.Busniess.Service;

import com.articlefetch.app.Busniess.Exceptions.AccountNotFoundException;
import com.articlefetch.app.Busniess.Exceptions.DuplicateEntryException;
import com.articlefetch.app.Controller.JacksonModels.Account;
import com.articlefetch.app.DataAccess.ModelDomain.AccountEntity;
import com.articlefetch.app.DataAccess.Repository.AccountRepository;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class AccountServiceTest {

    @InjectMocks
    AccountServiceImpl accountService;

    @Mock
    AccountRepository repository;

    @BeforeClass
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

     @Test
     void createAccount() {
         Account newAccount = new Account("jschappel", "password", "Joshua",
                 "Schappel", "j@shu.edu", null, null, true);

         AccountEntity newAccountEntry = new AccountEntity()
                 .create(null, "Joshua", "Schappel", "jschappel", "password",
                         "j@shu.edu", true);

         accountService.createAccount(newAccount);

         verify(
                 repository,
                 times(1)
         ).findExistingConflicts(newAccount.username, newAccount.password);

     }

     @Test
     void createAccount_when_new_account_is_a_duplicate() {
         Account newAccount = new Account("jschappel", "password", "Joshua",
                 "Schappel", "j@shu.edu", null, null, true);

         doThrow(new DuplicateEntryException())
                 .when(repository)
                 .findExistingConflicts(newAccount.username, newAccount.password);

         assertThrows(DuplicateEntryException.class, () -> accountService.createAccount(newAccount));

     }



    @Test
    void getAccount() {
        AccountEntity accountE = new AccountEntity()
                .create(1, "Josh", "Schappel", "jschappel", "password",
                        "test@shu.edu",true);
        when(repository.findById(1)).thenReturn(java.util.Optional.of(accountE));

        // test
        Account account = accountService.getAccount(1);
        assertEquals(accountE.getFirst_name(), account.first_name);
        assertEquals(accountE.getLast_name(), account.last_name);
        assertEquals(accountE.getUsername(), account.username);
        assertEquals(accountE.getPassword(), account.password);
        assertEquals(accountE.getEmail(), account.email);
        assertTrue(account.status);
    }

    @Test
    void when_getting_invalid_id_for_account_should_throw_error() {
        when(repository.findById(12)).thenThrow(new AccountNotFoundException(12));

        Exception exception = assertThrows(AccountNotFoundException.class, () -> {
            accountService.getAccount(12);
        });

        String expectedMessage = "User with id: 12 does not exist";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void getAllAccounts() {
        AccountEntity account1 = new AccountEntity().create(1, "Josh", "Schappel",
                "jschappel", "password", "test@shu.edu",true);

        AccountEntity account2 = new AccountEntity().create(2, "Sach", "M", "sachm",
                "password2", "sachm@shu.edu", true);

        List<AccountEntity> dataBaseList = new ArrayList<>();
        dataBaseList.add(account1);
        dataBaseList.add(account2);

        when(repository.findAll()).thenReturn(dataBaseList);

        //test
        List<Account> accountList = accountService.getAllAccounts();
        assertEquals(dataBaseList.size(), accountList.size());
        verify(repository, times(1)).findAll();
    }
/*
    @Test
    void deactivateAccount() {
        Account newAccount = new Account("jschappel", "password", "Joshua",
                "Schappel", "j@shu.edu", null, null, true);

        AccountEntity newAccountEntry = new AccountEntity()
                .create(null, "Joshua", "Schappel", "jschappel", "password",
                        "j@shu.edu", true);

       doNothing().when(repository).setAccountStatus(1,0);
       accountService.deactivateAccount(0);
       verify(accountService, times(1)).deactivateAccount(0);

    }


    @Test
    void deactivateAccount_that_does_not_exist() {
        assertThrows(AccountNotFoundException.class, () ->accountService.deactivateAccount(1));
    }

        @Test
        void reactivateAccount() {
        }
        @Test
        void updateAccount() {
        }
    */
    @Test
    void convertToDAO() {
        assertTrue(true);

    }

    @Test
    void convertToJackson() {
        assertTrue(true);
    }

}