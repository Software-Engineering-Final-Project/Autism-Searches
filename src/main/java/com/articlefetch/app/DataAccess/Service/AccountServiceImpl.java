package com.articlefetch.app.DataAccess.Service;

import com.articlefetch.app.DataAccess.ModelDomain.AccountEntity;
import com.articlefetch.app.DataAccess.Repository.AccountDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountDAO accountDAO;

    @Autowired
    public AccountServiceImpl(AccountDAO accountDAO) {
        this.accountDAO = accountDAO;
    }

    @Override
    public void createAccount(AccountEntity account) {
        accountDAO.save(account);
    }

    @Override
    public AccountEntity readAccount(Integer account_id) {
        //TODO: Add better error handling
        return accountDAO.findById(account_id).orElse(null);
    }

    @Override
    public List<AccountEntity> getAllAccounts() {
        return accountDAO.findAll();
    }

    @Override
    public void deactivateAccount(Integer account_id) {
        //TODO: Add better error handling
        AccountEntity currentAccount = accountDAO.findById(account_id).orElseThrow();
        currentAccount.setStatus(false);
        accountDAO.save(currentAccount);
    }

    @Override
    public void reactivateAccount(Integer account_id) {
        //TODO: Add better error handling
        AccountEntity currentAccount = accountDAO.findById(account_id).orElseThrow();
        currentAccount.setStatus(true);
        accountDAO.save(currentAccount);
    }
}
