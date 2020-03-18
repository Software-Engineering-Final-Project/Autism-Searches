package com.articlefetch.app.Busniess.Service;

import com.articlefetch.app.Busniess.Exceptions.AccountNotFoundException;
import com.articlefetch.app.Busniess.Exceptions.InvalidPasswordException;
import com.articlefetch.app.Controller.JacksonModels.Account;
import com.articlefetch.app.Controller.JacksonModels.AccountCreate;
import com.articlefetch.app.DataAccess.ModelDomain.AccountEntity;
import com.articlefetch.app.DataAccess.Repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    AccountRepository accountRepository;

    @Override
    public Account validateAccount(String username, String password)
            throws AccountNotFoundException, InvalidPasswordException, IOException {
        AccountEntity db_account = accountRepository.findAccountByUserName(username)
                .orElseThrow( () -> new AccountNotFoundException(username));

        if(!db_account.getPassword().equals(password)){
             throw new InvalidPasswordException(password);
        }
        return Mapper.from(db_account);
    }
}
