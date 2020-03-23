package com.articlefetch.app.Busniess.Exceptions;

public class StarredCategoriesNotFoundExeption extends RuntimeException {

    public StarredCategoriesNotFoundExeption(Integer id) {
        super(String.format("StarredCategory id: %d is not a valid id", id));
    }

}
