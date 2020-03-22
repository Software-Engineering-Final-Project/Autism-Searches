package com.articlefetch.app.Busniess.Service;

import com.articlefetch.app.Busniess.Exceptions.CategoryNotFoundException;
import com.articlefetch.app.Busniess.Exceptions.DuplicateEntryException;
import com.articlefetch.app.Controller.JacksonModels.Category;

import java.io.IOException;
import java.util.List;

public interface CategoryService {
    public List<Category> getAllCategories();
    public Category getCategory(Integer id) throws CategoryNotFoundException, IOException;
    public Category updateCategory(Integer id, Category category) throws CategoryNotFoundException, IOException;
    public void createCategory(Category category) throws DuplicateEntryException;

}