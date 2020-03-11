package com.articlefetch.app.Busniess.Service;

import com.articlefetch.app.Busniess.Exceptions.AccountNotFoundException;
import com.articlefetch.app.Busniess.Exceptions.DuplicateEntryException;
import com.articlefetch.app.Controller.JacksonModels.Account;
import com.articlefetch.app.Controller.JacksonModels.AccountStatus;
import com.articlefetch.app.DataAccess.ModelDomain.AccountEntity;

import java.util.List;

public interface AccountService {

    public void createAccount(Account account) throws DuplicateEntryException;
    public Account getAccount(Integer account_id) throws AccountNotFoundException;
    public List<Account> getAllAccounts();
    public void deactivateAccount(Integer id) throws AccountNotFoundException;
    public void reactivateAccount(Integer id) throws AccountNotFoundException;
    public Account updateAccount(Integer id, Account account) throws AccountNotFoundException;

}
