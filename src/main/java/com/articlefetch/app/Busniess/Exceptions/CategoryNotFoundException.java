package com.articlefetch.app.Busniess.Exceptions;

public class CategoryNotFoundException extends RuntimeException {
    public CategoryNotFoundException(Integer id) {
        super(String.format("Category id: %d is not a valid id", id));
    }
}
