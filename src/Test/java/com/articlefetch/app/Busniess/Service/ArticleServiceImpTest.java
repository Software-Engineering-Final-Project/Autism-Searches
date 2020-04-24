package com.articlefetch.app.Busniess.Service;

import com.articlefetch.app.Busniess.Exceptions.ArticleNotFoundException;
import com.articlefetch.app.Busniess.Exceptions.DuplicateEntryException;
import com.articlefetch.app.Controller.JacksonModels.Article;
import com.articlefetch.app.DataAccess.ModelDomain.ArticleEntity;
import com.articlefetch.app.DataAccess.Repository.ArticleRepository;
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
public class ArticleServiceImpTest {

    @InjectMocks
    ArticleServiceImpl articleService;

    @Mock
    ArticleRepository repository;

    @BeforeClass
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void createArticle() {
        Article newArticleCreate = new Article(1, "AutizABC", "Joshua",
                "Schappel.com", 2);

        ArticleEntity articleE = new ArticleEntity()
                .create(1, "AutizABC", "Schappel", "jschappel.com",
                        2);

        when(repository.save(any(ArticleEntity.class))).thenReturn(articleE);

        articleService.createArticle(newArticleCreate);

        verify(
                repository,
                times(1)
        ).findExistingConflicts(newArticleCreate.getArticle_title(), newArticleCreate.getAuthors());
    }

    @Test
    void createArticle_when_new_article_is_a_duplicate() {
        Article newArticleCreate = new Article(1, "AutizABC", "Joshua",
                "Schappel.com", 12);

        doThrow(new DuplicateEntryException())
                .when(repository)
                .findExistingConflicts(newArticleCreate.getArticle_title(), newArticleCreate.getAuthors());

        assertThrows(DuplicateEntryException.class, () -> articleService.createArticle(newArticleCreate));

    }

    @Test
    void getArticle() throws IOException {
        ArticleEntity articleE = new ArticleEntity()
                .create(1, "AutizABC", "Schappel", "jschappel.com", 32);
        when(repository.findById(1)).thenReturn(java.util.Optional.of(articleE));

        // test
        Article article = articleService.getArticle(1);
        assertEquals(articleE.getArticleTitle(), article.getArticle_title());
        assertEquals(articleE.getArticleAuthors(), article.getAuthors());
        assertEquals(articleE.getArticleDesc(), article.getArticle_desc());
        assertEquals(articleE.getfk_category_id(), article.getFk_categories_id());
    }

    @Test
    void when_getting_invalid_id_for_article_should_throw_error() {
        when(repository.findById(12)).thenThrow(new ArticleNotFoundException(12));

        Exception exception = assertThrows(ArticleNotFoundException.class, () -> {
            articleService.getArticle(12);
        });

        String expectedMessage = "Article id: 12 is not a valid id";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void getAllArticles() {
        ArticleEntity article1 = new ArticleEntity().create(1, "NewStuff", "Schapp",
                "jschappel.com", 2);

        ArticleEntity article2 = new ArticleEntity().create(2, "NewerSuff", "Jon", "bareli.com",
                2);

        List<ArticleEntity> dataBaseList = new ArrayList<>();
        dataBaseList.add(article1);
        dataBaseList.add(article2);

        when(repository.findAll()).thenReturn(dataBaseList);

        //test
        List<Article> articleCreateList = articleService.getAllArticles();
        assertEquals(dataBaseList.size(), articleCreateList.size());
        verify(repository, times(1)).findAll();
    }

    @Test
    void update_article() throws IOException {
        Article updateArticle = new Article(12, "AutizABC", "Joshua",
                "Schappel.com", 4);

        ArticleEntity newArticleEntry = new ArticleEntity()
                .create(12, "AutizABC", "Joshua", "Schappel.com",
                        4);


        when(repository.findById(12)).thenReturn(Optional.of(newArticleEntry));
        Article updatedArticleCreate = articleService.updateArticle(12, updateArticle);

        Article article = articleService.getArticle(12);
        assertEquals(updatedArticleCreate.getId(), article.getId());
        assertEquals(updatedArticleCreate.getArticle_title(), article.getArticle_title());
        assertEquals(updatedArticleCreate.getArticle_desc(), article.getArticle_desc());
        assertEquals(updatedArticleCreate.getAuthors(), article.getAuthors());
        assertEquals(updatedArticleCreate.getFk_categories_id(), article.getFk_categories_id());

    }

    @Test
    void updateArticle_that_does_not_exist() {
        Article newArticleCreate = new Article(133, "AutizABC", "Joshua",
                "Schappel.com", 2);

        when(repository.findById(133)).thenThrow(ArticleNotFoundException.class);

        assertThrows(ArticleNotFoundException.class, () -> {
            articleService.updateArticle(133, newArticleCreate);
        });
    }


}
