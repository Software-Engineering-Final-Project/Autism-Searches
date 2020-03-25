package com.articlefetch.app.Busniess.Service;

import com.articlefetch.app.Busniess.Hashing.SHAhashing;
import com.articlefetch.app.Busniess.Exceptions.AccountNotFoundException;
import com.articlefetch.app.Busniess.Exceptions.InvalidPasswordException;
import com.articlefetch.app.Controller.JacksonModels.Account;
import com.articlefetch.app.Controller.JacksonModels.AccountCreate;
import com.articlefetch.app.DataAccess.ModelDomain.AccountEntity;
import com.articlefetch.app.DataAccess.Repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    AccountRepository accountRepository;

    @Override
    public Account validateAccount(String username, String password)
            throws AccountNotFoundException, InvalidPasswordException, IOException, InvalidKeySpecException, NoSuchAlgorithmException {
        AccountEntity db_account = accountRepository.findAccountByUserName(username)
                .orElseThrow( () -> new AccountNotFoundException(username));

        if(!SHAhashing.checkPassword(password, db_account.getPassword())){
             throw new InvalidPasswordException(password);
        }
        return Mapper.from(db_account);
    }
}
