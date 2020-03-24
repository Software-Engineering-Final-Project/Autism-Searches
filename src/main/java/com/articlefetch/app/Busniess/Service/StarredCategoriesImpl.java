package com.articlefetch.app.Busniess.Service;

import com.articlefetch.app.Busniess.Exceptions.DuplicateEntryException;
import com.articlefetch.app.Busniess.Exceptions.StarredCategoriesNotFoundExeption;
import com.articlefetch.app.Controller.JacksonModels.StarredCategories;
import com.articlefetch.app.DataAccess.ModelDomain.StarredCategoriesEntity;
import com.articlefetch.app.DataAccess.Repository.StarredCategoriesRepository;
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
public class StarredCategoriesImpl implements StarredCategoriesService{

    @Autowired StarredCategoriesRepository starredCategoriesRepository;

    @Transactional
    @Override
    public Integer createStarredCategories(List<StarredCategories> categories) throws DuplicateEntryException {
        categories.forEach( (category) -> {
            try {
                if(!starredCategoriesRepository.findExistingConflicts(category.getStarred_categories_name()).isEmpty()){
                    throw new DuplicateEntryException("Category is already in use");
                }
                starredCategoriesRepository.save(Mapper.from(category));
            } catch(DuplicateEntryException e) {
;                throw e;
            } catch (Exception e){
                throw e;
            }
        });

        return categories.size();
    }

    @Override
    public List<StarredCategories> getAllStarredCategories() {
        List<StarredCategoriesEntity> list = (List<StarredCategoriesEntity>) starredCategoriesRepository.findAll();
        Stream<StarredCategories> stream = list.stream().map(  (Scategory) -> {
            try {
                return Mapper.from(Scategory);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        return stream.collect(Collectors.toList());
    }

    @Override
    public StarredCategories getStarredCategories(Integer id) throws StarredCategoriesNotFoundExeption, IOException {
        StarredCategoriesEntity entity = starredCategoriesRepository.findById(id)
                .orElseThrow(() -> new StarredCategoriesNotFoundExeption(id));
        return Mapper.from(entity);
    }

    @Transactional
    @Override
    public StarredCategories updateStarredCategories(Integer id, StarredCategories Scategory) throws StarredCategoriesNotFoundExeption, IOException {
        StarredCategoriesEntity entity = starredCategoriesRepository.findById(id).orElseThrow( () -> new StarredCategoriesNotFoundExeption(id));
        entity.setCategories_name(Scategory.getStarred_categories_name());

        starredCategoriesRepository.save(entity);
        return Mapper.from(entity);
    }


}
