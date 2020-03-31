package com.articlefetch.app.DataAccess.Repository;

import com.articlefetch.app.Busniess.Exceptions.ArticleNotFoundException;
import com.articlefetch.app.Controller.JacksonModels.Article;
import com.articlefetch.app.DataAccess.ModelDomain.ArticleEntity;
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
public class ArticleRepositoryTest {

    @Mock
    ArticleRepository articleDAO;

    @BeforeClass
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

//    @Test
//    void findByID() {
//        ArticleEntity articleE = new ArticleEntity()
//                .create(1, "AutismABC", "Schappel", "jschappel.com", 1,
//                        3);
//
//        when(articleDAO.findById(1)).thenReturn(java.util.Optional.ofNullable(articleE));
//        assertEquals(articleE, articleDAO.findById(1).get());
//    }
//
//    @Test
//    void findById_when_id_is_not_present() {
//        when(articleDAO.findById(2)).thenThrow(ArticleNotFoundException.class);
//        assertThrows(ArticleNotFoundException.class, () -> {
//            articleDAO.findById(2);
//        });
//    }
//
//


}
