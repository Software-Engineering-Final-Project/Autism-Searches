package com.articlefetch.app.Busniess.Exceptions;

public class InvalidPasswordException extends RuntimeException {

    public InvalidPasswordException(String username) {
        super(String.format("Invalid password for %s", username));
    }
}
