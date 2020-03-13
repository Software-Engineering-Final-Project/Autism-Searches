package com.articlefetch.app.Busniess.Service;

import com.articlefetch.app.Busniess.Exceptions.StarredCategoriesNotFoundExeption;
import com.articlefetch.app.Controller.JacksonModels.StarredCategories;
import com.articlefetch.app.DataAccess.ModelDomain.StarredCategoriesEntity;
import com.articlefetch.app.DataAccess.Repository.StarredCategoriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * This class is responsible for interfacing Hibernate data retrieval API for CategoryEntity
 */
@Service
public class StarredCategoriesImpl implements StarredCategoriesService, Conversion<StarredCategoriesEntity, StarredCategories>{

    @Autowired StarredCategoriesRepository starredCategoriesRepository;

    @Override
    public List<StarredCategories> allStarredCategories() {
        return null;
    }

    @Override
    public StarredCategories getStarredCategories(Integer id) throws StarredCategoriesNotFoundExeption {
        return null;
    }

    @Override
    public StarredCategoriesEntity convertToDAO(StarredCategories obj) {

        StarredCategoriesEntity entity = new StarredCategoriesEntity();
        entity.setCategories_name(obj.Starred_categories_name);
        entity.setStarred_categories_id(obj.id);
        return entity;
    }

    @Override
    public StarredCategories convertToJackson(StarredCategoriesEntity obj) {
        return new StarredCategories(obj.getStarred_categories_id(),
                obj.getCategories_name(),
                obj.getFK_account_id(),
                obj.getFK_categories_id());
    }

}
