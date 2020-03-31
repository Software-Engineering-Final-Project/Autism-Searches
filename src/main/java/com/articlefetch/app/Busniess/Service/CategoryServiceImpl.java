package com.articlefetch.app.Busniess.Service;

import com.articlefetch.app.Busniess.Exceptions.CategoryNotFoundException;
import com.articlefetch.app.Busniess.Exceptions.DuplicateEntryException;
import com.articlefetch.app.Busniess.Exceptions.InvalidDataInsertException;
import com.articlefetch.app.Controller.JacksonModels.Account;
import com.articlefetch.app.Controller.JacksonModels.Category;
import com.articlefetch.app.DataAccess.ModelDomain.CategoryEntity;
import com.articlefetch.app.DataAccess.Repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


/**
 * This class is responsible for interfacing Hibernate data retrieval API for CategoryEntity
 */
@Service
public class CategoryServiceImpl implements CategoryService{

    @Autowired CategoryRepository categoryRepository;

    @Transactional
    @Override
    public void createCategory(Category category) throws DuplicateEntryException {
        // Check if an account exists
        if(!categoryRepository.findExistingConflicts(category.getName()).isEmpty()) {
            throw new DuplicateEntryException();
        }
        categoryRepository.save(Mapper.from(category));
    }

    @Override
    public Category getCategory(Integer category_id) throws CategoryNotFoundException, IOException {
        CategoryEntity entity = categoryRepository.findById(category_id)
                .orElseThrow(() -> new CategoryNotFoundException(category_id));

        return Mapper.from(entity);
    }

    @Override
    public List<Category> getAllCategories(){
        List<CategoryEntity> list = (List<CategoryEntity>) categoryRepository.findAll();
        Stream<Category> stream = list.stream().map(Mapper::from);
        return stream.collect(Collectors.toList());
    }


    @Override
    public Category updateCategory(Integer id, Category category) throws CategoryNotFoundException, IOException {
        CategoryEntity entity = categoryRepository.findById(id).orElseThrow( () -> new CategoryNotFoundException(id));
        entity.setDescription(category.description);
        entity.setCategoryName(category.name);

        categoryRepository.save(entity);
        return Mapper.from(entity);
    }

}
