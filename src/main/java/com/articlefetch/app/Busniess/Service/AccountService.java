package com.articlefetch.app.Busniess.Service;

import com.articlefetch.app.Controller.JacksonModels.Account;
import com.articlefetch.app.Controller.JacksonModels.AccountStatus;
import com.articlefetch.app.DataAccess.ModelDomain.AccountEntity;

import java.util.List;

public interface AccountService {

    public AccountStatus createAccount(Account account);
    public Account getAccount(Integer account_id);
    public List<Account> getAllAccounts();
    public AccountStatus deactivateAccount(String username);
    public AccountStatus reactivateAccount(String username);
    public AccountStatus updateAccount(String username);
    public AccountStatus updateAccount(Integer id);

}
