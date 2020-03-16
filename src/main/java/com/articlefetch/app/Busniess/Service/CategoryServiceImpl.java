package com.articlefetch.app.Busniess.Service;

import com.articlefetch.app.Busniess.Exceptions.CategoryNotFoundException;
import com.articlefetch.app.Controller.JacksonModels.Category;
import com.articlefetch.app.DataAccess.ModelDomain.CategoryEntity;
import com.articlefetch.app.DataAccess.Repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


/**
 * This class is responsible for interfacing Hibernate data retrieval API for CategoryEntity
 */
@Service
public class CategoryServiceImpl implements CategoryService, Conversion<CategoryEntity, Category>{

    @Autowired CategoryRepository categoryRepository;

    @Override
    public List<Category> getAllCategories(){
        List<CategoryEntity> list = (List<CategoryEntity>) categoryRepository.findAll();
        Stream<Category> stream = list.stream().map( (category) -> convertToJackson(category));
        return stream.collect(Collectors.toList());
    }

    @Override
    public Category getCategory(Integer category_id) throws CategoryNotFoundException {
        return convertToJackson( categoryRepository.findById(category_id)
                .orElseThrow(() -> new CategoryNotFoundException(category_id)));
    }

    @Override
    public CategoryEntity convertToDAO(Category obj) {

        CategoryEntity entity = new CategoryEntity();
        entity.setCategoryName(obj.name);
        entity.setDescription(obj.description);
        entity.setId(obj.id);
        return entity;
    }

    @Override
    public Category convertToJackson(CategoryEntity obj) {
        return new Category(obj.getId(), obj.getCategoryName(), obj.getDescription());
    }
}
