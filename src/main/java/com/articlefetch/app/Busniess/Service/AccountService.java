package com.articlefetch.app.Busniess.Service;

import com.articlefetch.app.Busniess.Exceptions.AccountNotFoundException;
import com.articlefetch.app.Busniess.Exceptions.DuplicateEntryException;
import com.articlefetch.app.Controller.JacksonModels.Account;
import com.articlefetch.app.Controller.JacksonModels.AccountCreate;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;

public interface AccountService {

    public Integer createAccount(AccountCreate accountCreate) throws DuplicateEntryException, NoSuchAlgorithmException, InvalidKeySpecException;
    public Account getAccount(Integer account_id) throws AccountNotFoundException, IOException;
    public List<Account> getAllAccounts();
    public void deactivateAccount(Integer id) throws AccountNotFoundException;
    public void reactivateAccount(Integer id) throws AccountNotFoundException;
    public Account updateAccount(Integer id, Account accountCreate) throws AccountNotFoundException, IOException;

}
