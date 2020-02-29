package com.articlefetch.app.Busniess.Exceptions;

import com.articlefetch.app.Controller.JacksonModels.Account;

public class DuplicateEntryException extends RuntimeException {

    public DuplicateEntryException() {
        super("Certain fields already Exists");
    }
}
