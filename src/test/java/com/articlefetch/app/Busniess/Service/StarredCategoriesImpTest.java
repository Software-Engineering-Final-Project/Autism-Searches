package com.articlefetch.app.Busniess.Service;

import com.articlefetch.app.Controller.JacksonModels.StarredCategories;
import com.articlefetch.app.DataAccess.ModelDomain.StarredCategoriesEntity;
import com.articlefetch.app.DataAccess.Repository.StarredCategoriesRepository;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class StarredCategoriesImpTest {


    @InjectMocks
    StarredCategoriesImpl starredCategoriesService;

    @Mock
    StarredCategoriesRepository repository;

    @BeforeClass
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void createStarredCategories() {
        StarredCategories newSCategories = new StarredCategories(1, "Research", 23,
                76);

        starredCategoriesService.createStarredCategories(newSCategories);

        verify(
                repository,
                times(1)
        ).findExistingConflicts(newSCategories.getStarred_categories_name());
    }










}
