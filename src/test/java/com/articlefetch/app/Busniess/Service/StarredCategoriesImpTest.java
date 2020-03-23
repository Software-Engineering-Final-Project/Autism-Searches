package com.articlefetch.app.Busniess.Service;

import com.articlefetch.app.Busniess.Exceptions.StarredCategoriesNotFoundExeption;
import com.articlefetch.app.Busniess.Exceptions.DuplicateEntryException;
import com.articlefetch.app.Busniess.Exceptions.StarredCategoriesNotFoundExeption;
import com.articlefetch.app.Controller.JacksonModels.Article;
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

        StarredCategoriesEntity starredCategoreisE = new StarredCategoriesEntity()
                .create(1, "Research", 23, 76);

        when(repository.save(any(StarredCategoriesEntity.class))).thenReturn(starredCategoreisE);

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
    void getStarredCategories() throws IOException {
        StarredCategoriesEntity SCategoriesE = new StarredCategoriesEntity()
                .create(1, "Research", 34, 54);
        when(repository.findById(1)).thenReturn(java.util.Optional.of(SCategoriesE));

        // test
        StarredCategories starredCategories = starredCategoriesService.getStarredCategories(1);
        assertEquals(SCategoriesE.getCategories_name(), starredCategories.getStarred_categories_name());
        assertEquals(SCategoriesE.getFK_account_id(), SCategoriesE.getFK_account_id());
        assertEquals(SCategoriesE.getFK_categories_id(), SCategoriesE.getFK_categories_id());
    }

    @Test
    void when_getting_invalid_id_for_starredCategories_should_throw_error() {
        when(repository.findById(12)).thenThrow(new StarredCategoriesNotFoundExeption(12));

        Exception exception = assertThrows(StarredCategoriesNotFoundExeption.class, () -> {
            starredCategoriesService.getStarredCategories(12);
        });

        String expectedMessage = "StarredCategory id: 12 is not a valid id";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void getAllArticles() {
        StarredCategoriesEntity starredCategories1 = new StarredCategoriesEntity()
                .create(1, "Research", 32,3);

        StarredCategoriesEntity starredCategories2 = new StarredCategoriesEntity()
                .create(2, "Medic", 45, 54);

        List<StarredCategoriesEntity> dataBaseList = new ArrayList<>();
        dataBaseList.add(starredCategories1);
        dataBaseList.add(starredCategories2);

        when(repository.findAll()).thenReturn(dataBaseList);

        //test
        List<StarredCategories> starredCategoriesCreateList = starredCategoriesService.getAllStarredCategories();
        assertEquals(dataBaseList.size(), starredCategoriesCreateList.size());
        verify(repository, times(1)).findAll();
    }

    @Test
    void update_starredCategories() throws IOException {
        StarredCategories updateStarredCategories = new StarredCategories(12, "Research",
                32,5);

        StarredCategoriesEntity newStarredCategoriesEntry = new StarredCategoriesEntity()
                .create(12, "Research", 32, 5);


        when(repository.findById(12)).thenReturn(Optional.of(newStarredCategoriesEntry));
        StarredCategories updatedStarredCategoriesCreate = starredCategoriesService.updateStarredCategories(12, updateStarredCategories);

        StarredCategories starredCategories = starredCategoriesService.getStarredCategories(12);
        assertEquals(updatedStarredCategoriesCreate.getId(), starredCategories.getId());
        assertEquals(updatedStarredCategoriesCreate.getStarred_categories_name(), starredCategories.getStarred_categories_name());
        assertEquals(updatedStarredCategoriesCreate.getFk_account_id(), starredCategories.getFk_account_id());
        assertEquals(updatedStarredCategoriesCreate.getFk_categories_id(), starredCategories.getFk_categories_id());

    }

    @Test
    void updateStarredCategories_that_does_not_exist() {
        StarredCategories newStarredCategoriesCreate = new StarredCategories(133, "Research", 21,
                2);

        when(repository.findById(133)).thenThrow(StarredCategoriesNotFoundExeption.class);

        assertThrows(StarredCategoriesNotFoundExeption.class, () -> {
            starredCategoriesService.updateStarredCategories(133, newStarredCategoriesCreate);
        });
    }









}
