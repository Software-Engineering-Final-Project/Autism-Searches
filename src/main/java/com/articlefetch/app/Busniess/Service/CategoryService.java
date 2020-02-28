package com.articlefetch.app.Busniess.Service;

import com.articlefetch.app.Controller.JacksonModels.Category;

import java.util.List;

public interface CategoryService {
    public List<Category> allCategories();
    public Category getCategory(String name);
    public boolean removeCategory(String name);
}