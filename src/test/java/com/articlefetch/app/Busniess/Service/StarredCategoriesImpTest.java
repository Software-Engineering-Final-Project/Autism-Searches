package com.articlefetch.app.Busniess.Service;

import com.articlefetch.app.Busniess.Exceptions.DuplicateEntryException;
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

    @Test
    void createStarredCategories_when_new_starredCategories_is_a_duplicate() {
        StarredCategories newStarredCategoreis = new StarredCategories(1, "Research", 32,
                65);

        doThrow(new DuplicateEntryException())
                .when(repository)
                .findExistingConflicts(newStarredCategoreis.getStarred_categories_name());

        assertThrows(DuplicateEntryException.class, () -> starredCategoriesService.createStarredCategories(newStarredCategoreis));

    }

    @Test
    void getArticle() throws IOException {
        StarredCategoriesEntity SCategoriesE = new StarredCategoriesEntity()
                .create(1, "Research", 34, 54);
        when(repository.findById(1)).thenReturn(java.util.Optional.of(SCategoriesE));

        // test
        StarredCategories starredCategories = starredCategoriesService.getStarredCategories(1);
        assertEquals(SCategoriesE.getCategories_name(), starredCategories.getStarred_categories_name());
        assertEquals(null, SCategoriesE.getFK_account_id());
        assertEquals(null, SCategoriesE.getStarred_categories_id());
    }









}
