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

    @Test
    void findByID() {
        ArticleEntity articleE = new ArticleEntity()
                .create(1, "AutismABC", "Schappel", "jschappel.com", 1,
                        3);

        when(articleDAO.findById(1)).thenReturn(java.util.Optional.ofNullable(articleE));
        assertEquals(articleE, articleDAO.findById(1).get());
    }

    @Test
    void findById_when_id_is_not_present() {
        when(articleDAO.findById(2)).thenThrow(ArticleNotFoundException.class);
        assertThrows(ArticleNotFoundException.class, () -> {
            articleDAO.findById(2);
        });
    }

    @Test
    void findAll() {
        ArticleEntity articleE1 = new ArticleEntity()
                .create(1, "Josh", "Schappel", "jschappel.com", 21,
                        5);
        ArticleEntity articleE2 = new ArticleEntity()
                .create(2, "Jon", "Bar Eli", "Jbareli.com", 32,
                        4);
        List<ArticleEntity> articleList = new ArrayList<>();
        articleList.add(articleE1);
        articleList.add(articleE2);

        when(articleDAO.findAll()).thenReturn(articleList);

        assertEquals(articleList, articleDAO.findAll());
    }

    @Test
    void findAll_when_table_is_empty() {
        List<ArticleEntity> articleList = new ArrayList<>();
        when(articleDAO.findAll()).thenReturn(articleList);

        assertEquals(articleList, articleDAO.findAll());
    }

    @Test
    void find_existing_conflicts_no_conflict() {
        List<ArticleEntity> articleList = new ArrayList<>();
        when(articleDAO.findExistingConflicts("Josh", "jschappel.com"))
                .thenReturn(articleList);

        assertTrue(articleDAO.findExistingConflicts("Joshua", "jschappel.com").isEmpty());
    }

    @Test
    void find_existing_conflicts_with_conflict() {
        ArticleEntity articleE1 = new ArticleEntity()
                .create(1, "Josh", "Schappel", "jschappel.com", 1,
                        2);
        List<ArticleEntity> accountList = new ArrayList<>();
        accountList.add(articleE1);


        when(articleDAO.findExistingConflicts("Joshua", "jschappel.com")).thenReturn(accountList);

        assertTrue(!articleDAO.findExistingConflicts("Joshua", "jschappel.com").isEmpty());
    }

    @Test
    void find_article_by_name() {
        ArticleEntity articleE1 = new ArticleEntity()
                .create(1, "Josh", "Schappel", "jschappel.com", 1,
                        2);

        when(articleDAO.findArticleByname("Josh")).thenReturn(Optional.of(articleE1));

        ArticleEntity e = articleDAO.findArticleByname("Josh").get();
        assertEquals(e.getArticleName(), articleE1.getArticleName());
        assertEquals(e.getArticleSite(), articleE1.getArticleSite());
        assertEquals(e.getArticleAuthors(), articleE1.getArticleAuthors());
        assertEquals(e.getStaredArticles_id(), articleE1.getStaredArticles_id());
        assertEquals(e.getFK_categories_id(), articleE1.getFK_categories_id());
        assertEquals(e.getFK_account_id(), articleE1.getFK_account_id());

    }

    @Test
    void find_article_bu_name_that_does_not_exist() {
        when(articleDAO.findArticleByname("Josh")).thenThrow(ArticleNotFoundException.class);

        assertThrows(ArticleNotFoundException.class, () -> {
            articleDAO.findArticleByname("Josh");
        });
    }


}
