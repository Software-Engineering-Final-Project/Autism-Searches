package com.articlefetch.app.DataAccess.Service;

import com.articlefetch.app.DataAccess.ModelDomain.AccountEntity;

import java.util.List;

public interface AccountService {

    public void createAccount(AccountEntity account);
    public AccountEntity readAccount(Integer account_id);
    public List<AccountEntity> getAllAccounts();
    public void deactivateAccount(Integer account_id);
    public void reactivateAccount(Integer account_id);

}
