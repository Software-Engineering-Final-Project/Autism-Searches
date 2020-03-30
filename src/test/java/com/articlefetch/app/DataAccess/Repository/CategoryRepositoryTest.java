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
    AccountRepository categoryDAO;

    @BeforeClass
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

//    @Test
//    void findByID() {
//        CategoryEntity categoryE = new CategoryEntity()
//                .create(1, "Research", "Bla Bla Bla");
//
//        when(categoryDAO.findById(1)).thenReturn(java.util.Optional.ofNullable(categoryE));
//        assertEquals(categoryE, categoryDAO.findById(1).get());
//    }

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
                .create(1, "Research", "Bla Bla Bla");

        List<CategoryEntity> articleList = new ArrayList<>();
        articleList.add(categoryE1);
        articleList.add(categoryE2);

        when(categoryDAO.findAll()).thenReturn(articleList);

        assertEquals(articleList, categoryDAO.findAll());
    }

    @Test
    void findAll_when_table_is_empty() {
        List<CategoryEntity> articleList = new ArrayList<>();
        when(categoryDAO.findAll()).thenReturn(articleList);

        assertEquals(articleList, categoryDAO.findAll());
    }




}
