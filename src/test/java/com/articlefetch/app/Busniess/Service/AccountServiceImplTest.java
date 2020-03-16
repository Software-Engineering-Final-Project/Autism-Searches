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

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    @Test
    void reactivateAccount() {
        // Return 1 because only one row should be affected
        when(repository.setAccountStatus(1,12)).thenReturn(Optional.of(1));

        accountService.reactivateAccount(12);
        verify(repository, times(1)).setAccountStatus(1,12);
    }

    @Test
    void reactivateAccount_when_invalid_id() {
        when(repository.setAccountStatus(1,12)).thenThrow(AccountNotFoundException.class);
        assertThrows(AccountNotFoundException.class, () -> {
            accountService.deactivateAccount(12);
        });
    }

    @Test
    void deactivateAccount() {
        // Return 1 because only one row should be affected
       when(repository.setAccountStatus(0, 1)).thenReturn(Optional.of(1));

       accountService.deactivateAccount(1);
       verify(repository, times(1)).setAccountStatus(0,1);

    }

    @Test
    void deactivateAccount_when_invalid_id() {
        when(repository.setAccountStatus(0,1)).thenThrow(AccountNotFoundException.class);
        assertThrows(AccountNotFoundException.class, () -> {
            accountService.deactivateAccount(1);
        });
    }

    @Test
    void update_account() {
        Account newAccount = new Account("jschappel", "password", "Joshua",
                "Schappel", "j@shu.edu", 12, null, true);

        AccountEntity newAccountEntry = new AccountEntity()
                .create(12, "Joshua", "Schappel", "jschappel", "password",
                        "j@shu.edu", true);

        when(repository.findById(12)).thenReturn(Optional.of(newAccountEntry));
        Account updatedAccount = accountService.updateAccount(12, newAccount);

        Account account = accountService.getAccount(12);
        assertEquals(updatedAccount.getId(), account.id);
        assertEquals(updatedAccount.getFirst_name(), account.first_name);
        assertEquals(updatedAccount.getLast_name(), account.last_name);
        assertEquals(updatedAccount.getUsername(), account.username);
        assertEquals(updatedAccount.getPassword(), account.password);
        assertEquals(updatedAccount.getEmail(), account.email);
        assertEquals(updatedAccount.getPath(), account.path);
        assertTrue(account.status);

    }

    @Test
    void updateAccount_that_does_not_exist() {
        Account newAccount = new Account("jschappel", "password", "Joshua",
                "Schappel", "j@shu.edu", 143, null, true);

        when(repository.findById(143)).thenThrow(AccountNotFoundException.class);

        assertThrows(AccountNotFoundException.class, () -> {
            accountService.updateAccount(143, newAccount);
        });
    }
}