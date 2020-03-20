package com.articlefetch.app.DataAccess.Repository;

import com.articlefetch.app.Busniess.Exceptions.AccountNotFoundException;
import com.articlefetch.app.DataAccess.ModelDomain.AccountEntity;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class AccountCreateRepositoryTest {

    @Mock
    AccountRepository accountDAO;

    @BeforeClass
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void findByID() {
        AccountEntity accountE = new AccountEntity()
                .create(1, "Josh", "Schappel", "jschappel", "password",
                        "test@shu.edu", "/Images/default_user.png", true);

        when(accountDAO.findById(1)).thenReturn(java.util.Optional.ofNullable(accountE));
        assertEquals(accountE, accountDAO.findById(1).get());
    }

    @Test
    void findById_when_id_is_not_present() {
        when(accountDAO.findById(2)).thenThrow(AccountNotFoundException.class);
        assertThrows(AccountNotFoundException.class, () -> {
            accountDAO.findById(2);
        });
    }

    @Test
    void findAll() {
        AccountEntity accountE1 = new AccountEntity()
                .create(1, "Josh", "Schappel", "jschappel", "password",
                        "test@shu.edu", "/Images/default_user.png", true);
        AccountEntity accountE2 = new AccountEntity()
                .create(1, "Josh", "Schappel", "jschappel", "password",
                        "test@shu.edu", "/Images/default_user.png", true);
        List<AccountEntity> accountList = new ArrayList<>();
        accountList.add(accountE1);
        accountList.add(accountE2);

        when(accountDAO.findAll()).thenReturn(accountList);

        assertEquals(accountList, accountDAO.findAll());
    }

    @Test
    void findAll_when_table_is_empty() {
        List<AccountEntity> accountList = new ArrayList<>();
        when(accountDAO.findAll()).thenReturn(accountList);

        assertEquals(accountList, accountDAO.findAll());
    }

    @Test
    void find_existing_conflicts_no_conflict() {
        List<AccountEntity> accountList = new ArrayList<>();
        when(accountDAO.findExistingConflicts("Josh", "password"))
                .thenReturn(accountList);

        assertTrue(accountDAO.findExistingConflicts("Joshua", "password").isEmpty());
    }

    @Test
    void find_existing_conflicts_with_conflict() {
        AccountEntity accountE1 = new AccountEntity()
                .create(1, "Josh", "Schappel", "jschappel", "password",
                        "test@shu.edu", "/Images/default_user.png", true);
        List<AccountEntity> accountList = new ArrayList<>();
        accountList.add(accountE1);


        when(accountDAO.findExistingConflicts("Joshua", "password")).thenReturn(accountList);

        assertTrue(!accountDAO.findExistingConflicts("Joshua", "password").isEmpty());
    }

    @Test
    void set_account_status_to_true() {
        AccountEntity accountE1 = new AccountEntity()
                .create(2, "Josh", "Schappel", "jschappel", "password",
                        "test@shu.edu", "/Images/default_user.png", true);

        when(accountDAO.setAccountStatus(1, 2)).thenReturn(Optional.of(1));

        assertEquals(1, accountDAO.setAccountStatus(1, 2).get());
    }

    @Test
    void set_account_status_to_false() {
        AccountEntity accountE1 = new AccountEntity()
                .create(2, "Josh", "Schappel", "jschappel", "password",
                        "test@shu.edu", "/Images/default_user.png",true);

        when(accountDAO.setAccountStatus(0, 2)).thenReturn(Optional.of(1));

        assertEquals(1, accountDAO.setAccountStatus(0, 2).get());
    }

    @Test
    void find_account_by_username() {
        AccountEntity accountE1 = new AccountEntity()
                .create(2, "Josh", "Schappel", "jschappel", "password",
                        "test@shu.edu", "/Images/default_user.png", true);

        when(accountDAO.findAccountByUserName("jschappel")).thenReturn(Optional.of(accountE1));

        AccountEntity e = accountDAO.findAccountByUserName("jschappel").get();
        assertEquals(e.getUsername(), accountE1.getUsername());
        assertEquals(e.getPassword(), accountE1.getPassword());
        assertEquals(e.getEmail(), accountE1.getEmail());
        assertEquals(e.getFirst_name(), accountE1.getFirst_name());
        assertEquals(e.getLast_name(), accountE1.getLast_name());
        assertEquals(e.getAccount_id(), accountE1.getAccount_id());
        assertEquals(e.getStatus(), accountE1.getStatus());
    }

    @Test
    void find_account_bu_username_that_does_not_exist() {
        when(accountDAO.findAccountByUserName("jschappel")).thenThrow(AccountNotFoundException.class);

        assertThrows(AccountNotFoundException.class, () -> {
            accountDAO.findAccountByUserName("jschappel");
        });
    }
}