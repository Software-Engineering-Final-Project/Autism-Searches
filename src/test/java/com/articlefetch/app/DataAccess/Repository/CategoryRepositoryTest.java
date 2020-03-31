package com.articlefetch.app.DataAccess.Repository;

import com.articlefetch.app.Busniess.Exceptions.CategoryNotFoundException;
import com.articlefetch.app.Controller.JacksonModels.Category;
import com.articlefetch.app.DataAccess.ModelDomain.CategoryEntity;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
public class CategoryRepositoryTest {


    @Mock
    CategoryRepository categoryDAO;

    @BeforeClass
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void findByID() {
        CategoryEntity categoryE = new CategoryEntity()
                .create(1, "Research", "Bla Bla Bla");

        when(categoryDAO.findById(1)).thenReturn(java.util.Optional.ofNullable(categoryE));
        assertEquals(categoryE, categoryDAO.findById(1).get());
    }
//
    @Test
    void findById_when_id_is_not_present() {
        when(categoryDAO.findById(2)).thenThrow(CategoryNotFoundException.class);
        assertThrows(CategoryNotFoundException.class, () -> {
            categoryDAO.findById(2);
        });
    }

    @Test
    void findAll() {
        CategoryEntity categoryE1 = new CategoryEntity()
                .create(1, "Research", "Bla Bla Bla");
        CategoryEntity categoryE2 = new CategoryEntity()
                .create(2, "Medical", "Bla Bla Bla");
        List<CategoryEntity> categoryList = new ArrayList<>();
        categoryList.add(categoryE1);
        categoryList.add(categoryE2);

        when(categoryDAO.findAll()).thenReturn(categoryList);

        assertEquals(categoryList, categoryDAO.findAll());
    }

    @Test
    void findAll_when_table_is_empty() {
        List<CategoryEntity> categoryList = new ArrayList<>();
        when(categoryDAO.findAll()).thenReturn(categoryList);

        assertEquals(categoryList, categoryDAO.findAll());
    }

    @Test
    void find_existing_conflicts_no_conflict() {
        List<CategoryEntity> categoryList = new ArrayList<>();
        when(categoryDAO.findExistingConflicts("Research"))
                .thenReturn(categoryList);

        assertTrue(categoryDAO.findExistingConflicts("Research1").isEmpty());
    }

    @Test
    void find_existing_conflicts_with_conflict() {
        CategoryEntity categoryE1 = new CategoryEntity()
                .create(1, "Research", "Bla Bla Bla");
        List<CategoryEntity> categoryList = new ArrayList<>();
        categoryList.add(categoryE1);

        when(categoryDAO.findExistingConflicts("Medical")).thenReturn(categoryList);

        assertTrue(!categoryDAO.findExistingConflicts("Medical").isEmpty());
    }

    @Test
    void find_account_by_username() {
        CategoryEntity categoryE1 = new CategoryEntity()
                .create(2, "Research", "Bla Bla Bla");

        when(categoryDAO.findCategoryByname("Research")).thenReturn(Optional.of(categoryE1));

        CategoryEntity e = categoryDAO.findCategoryByname("Research").get();
        assertEquals(e.getCategoryName(), categoryE1.getCategoryName());
        assertEquals(e.getDescription(), categoryE1.getDescription());
        assertEquals(e.getId(), categoryE1.getId());
    }

    @Test
    void find_account_bu_username_that_does_not_exist() {
        when(categoryDAO.findCategoryByname("Research")).thenThrow(CategoryNotFoundException.class);

        assertThrows(CategoryNotFoundException.class, () -> {
            categoryDAO.findCategoryByname("Research");
        });
    }

}
