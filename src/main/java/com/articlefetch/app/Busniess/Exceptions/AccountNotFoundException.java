package com.articlefetch.app.Busniess.Exceptions;

public class AccountNotFoundException extends RuntimeException {

    // given primary key
    public AccountNotFoundException(Integer id) {
        super(String.format("User with id: %d does not exist", id));
    }

    // given username
    public AccountNotFoundException(String username) {
        super(String.format("Username: %s does not exist", username));
    }
}
