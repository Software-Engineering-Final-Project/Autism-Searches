package com.articlefetch.app.Busniess.Service;

import com.articlefetch.app.Busniess.Exceptions.AccountNotFoundException;
import com.articlefetch.app.Busniess.Exceptions.InvalidPasswordException;
import com.articlefetch.app.Controller.JacksonModels.Account;
import com.articlefetch.app.Controller.JacksonModels.LoginValidation;
import com.articlefetch.app.DataAccess.ModelDomain.AccountEntity;
import com.articlefetch.app.DataAccess.Repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService, Conversion<AccountEntity, Account> {

    @Autowired
    AccountRepository accountRepository;

    @Override
    public Account validateAccount(String username, String password)
            throws AccountNotFoundException, InvalidPasswordException {
        AccountEntity db_account = accountRepository.findAccountByUserName(username)
                .orElseThrow( () -> new AccountNotFoundException(username));

        if(!db_account.getPassword().equals(password)){
             throw new InvalidPasswordException(password);
        }
        return convertToJackson(db_account);
    }

    @Override
    public AccountEntity convertToDAO(Account obj) {
        AccountEntity entity = new AccountEntity();
        entity.setUsername(obj.username);
        entity.setPassword(obj.password);
        entity.setFirst_name(obj.first_name);
        entity.setLast_name(obj.last_name);
        entity.setStatus(obj.status);
        entity.setEmail(obj.email);
        entity.setPath(obj.path);
        entity.setId_account(obj.id);
        return entity;
    }

    @Override
    public Account convertToJackson(AccountEntity obj) {
        return new Account(obj.getUsername(), obj.getPassword(), obj.getFirst_name(), obj.getLast_name(),
                obj.getEmail(),obj.getAccount_id(), obj.getPath(), obj.getStatus());
    }
}
