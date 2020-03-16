package com.articlefetch.app.Busniess.Service;

import com.articlefetch.app.Busniess.Exceptions.StarredCategoriesNotFoundExeption;
import com.articlefetch.app.Controller.JacksonModels.StarredCategories;

import java.util.List;

public interface StarredCategoriesService {
    public List<StarredCategories> getAllStarredCategories();
    public StarredCategories getStarredCategories(Integer id) throws StarredCategoriesNotFoundExeption;

}


