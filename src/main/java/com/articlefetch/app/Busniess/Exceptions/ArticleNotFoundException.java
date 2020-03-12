package com.articlefetch.app.Busniess.Exceptions;

public class ArticleNotFoundException extends RuntimeException{

    public ArticleNotFoundException(Integer id) {
        super(String.format("Article id: %d is not a valid id", id));
    }
}
