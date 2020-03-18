package com.articlefetch.app.Busniess.Service;

import com.articlefetch.app.Controller.JacksonModels.Account;
import com.articlefetch.app.Controller.JacksonModels.AccountCreate;
import com.articlefetch.app.DataAccess.ModelDomain.AccountEntity;
import org.apache.catalina.filters.ExpiresFilter;

import java.io.IOException;
import java.io.InputStream;

public final class Mapper {

    public static AccountEntity from(AccountCreate account) {
        return new AccountEntity().create(account.getId(), account.getFirst_name(), account.getLast_name(),
                account.getUsername(), account.getPassword(), account.getEmail(), account.getPath(),
                account.getStatus());
    }

    public static Account from(AccountEntity account) throws IOException {
         return new Account(account.getUsername(), account.getPassword(), account.getFirst_name(),
                 account.getLast_name(), account.getEmail(), account.getAccount_id(),
                 getImageAsByteArray(account.getPath()), account.getStatus());
    }


    private static byte[] getImageAsByteArray(String path) throws IOException {
        try {
            InputStream inputStream = Mapper.class
                    .getResourceAsStream(path);
            return inputStream.readAllBytes();
        } catch (Exception e) {
            // If there is not a valid path we will return null
            // TODO: Better Error Handling!
            return null;
        }
    }
}
