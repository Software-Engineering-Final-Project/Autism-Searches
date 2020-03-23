package com.articlefetch.app.Busniess.Service;

import com.articlefetch.app.Busniess.Exceptions.CategoryNotFoundException;
import com.articlefetch.app.Controller.JacksonModels.Category;
import com.articlefetch.app.DataAccess.ModelDomain.CategoryEntity;
import com.articlefetch.app.DataAccess.Repository.CategoryRepository;
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
public class CategoryServiceImpTest {

    @InjectMocks
    CategoryServiceImpl categoryService;

    @Mock
    CategoryRepository repository;

    @BeforeClass
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void createCategory() {
        Category newCategoryCreate = new Category(1, "Research", "Bla Bla Bla");

        CategoryEntity newCategoryEntry = new CategoryEntity()
                .create(1, "Research", "Bla Bla Bla");

        categoryService.createCategory(newCategoryCreate);

        verify(
                repository,
                times(1)
        ).findExistingConflicts(newCategoryCreate.getName());
    }

    @Test
    void getCategory() throws IOException {
        CategoryEntity categoryE = new CategoryEntity()
                .create(1, "AutizABC", "Blablalba");
        when(repository.findById(1)).thenReturn(java.util.Optional.of(categoryE));

        // test
        Category category = categoryService.getCategory(1);
        assertEquals(categoryE.getCategoryName(), category.getName());
        assertEquals(categoryE.getDescription(), category.getDescription());

    }

    @Test
    void when_getting_invalid_id_for_category_should_throw_error() {
        when(repository.findById(12)).thenThrow(new CategoryNotFoundException(12));

        Exception exception = assertThrows(CategoryNotFoundException.class, () -> {
            categoryService.getCategory(12);
        });

        String expectedMessage = "Category id: 12 is not a valid id";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void update_category() throws IOException {
        Category updateCategory = new Category(10, "Research", "BLALALALALALA");

        CategoryEntity newCategoryEntry = new CategoryEntity()
                .create(10, "Research", "BLALALALALALA");


        when(repository.findById(10)).thenReturn(Optional.of(newCategoryEntry));
        Category updatedCategoryCreate = categoryService.updateCategory(10, updateCategory);

        Category category = categoryService.getCategory(10);
        assertEquals(updatedCategoryCreate.getId(), category.getId());
        assertEquals(updatedCategoryCreate.getDescription(), category.getDescription());
        assertEquals(updatedCategoryCreate.getName(), category.getName());

    }

    @Test
    void updateArticle_that_does_not_exist() {
        Category newCategoryCreate = new Category(133, "Research", "Blalalalala");

        when(repository.findById(133)).thenThrow(CategoryNotFoundException.class);

        assertThrows(CategoryNotFoundException.class, () -> {
            categoryService.updateCategory(133, newCategoryCreate);
        });
    }


}
