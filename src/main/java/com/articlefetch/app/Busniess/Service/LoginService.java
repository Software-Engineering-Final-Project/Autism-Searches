package com.articlefetch.app.Busniess.Service;

import com.articlefetch.app.Busniess.Exceptions.AccountNotFoundException;
import com.articlefetch.app.Busniess.Exceptions.InvalidPasswordException;
import com.articlefetch.app.Controller.JacksonModels.Account;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public interface LoginService {
    public Account validateAccount(String username, String password)
            throws AccountNotFoundException, InvalidPasswordException, IOException, InvalidKeySpecException, NoSuchAlgorithmException;
}
