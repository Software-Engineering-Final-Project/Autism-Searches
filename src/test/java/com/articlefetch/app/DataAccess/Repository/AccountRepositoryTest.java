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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class AccountRepositoryTest {

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
                        "test@shu.edu", true);

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
                        "test@shu.edu", true);
        AccountEntity accountE2 = new AccountEntity()
                .create(1, "Josh", "Schappel", "jschappel", "password",
                        "test@shu.edu", true);
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
                        "test@shu.edu",true);
        List<AccountEntity> accountList = new ArrayList<>();
        accountList.add(accountE1);


        when(accountDAO.findExistingConflicts("Joshua", "password")).thenReturn(accountList);

        assertEquals(accountList, accountDAO.findExistingConflicts("Joshua", "password"));
    }
}