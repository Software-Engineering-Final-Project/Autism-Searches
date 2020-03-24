package com.articlefetch.app.Busniess.Service;

import com.articlefetch.app.Busniess.Exceptions.DuplicateEntryException;
import com.articlefetch.app.Busniess.Exceptions.StarredCategoriesNotFoundExeption;
import com.articlefetch.app.Controller.JacksonModels.Category;
import com.articlefetch.app.Controller.JacksonModels.StarredCategories;

import java.io.IOException;
import java.util.List;

public interface StarredCategoriesService {

    public Integer createStarredCategories(List<StarredCategories> categories) throws DuplicateEntryException;
    public List<StarredCategories> getAllStarredCategories();
    public StarredCategories getStarredCategories(Integer id) throws StarredCategoriesNotFoundExeption, IOException;
    public StarredCategories updateStarredCategories(Integer id, StarredCategories Scategory) throws StarredCategoriesNotFoundExeption, IOException;

}


