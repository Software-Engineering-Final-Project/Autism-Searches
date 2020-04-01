package com.articlefetch.app.Controller;

import com.articlefetch.app.Busniess.Exceptions.ArticleNotFoundException;
import com.articlefetch.app.Busniess.Exceptions.DuplicateEntryException;
import com.articlefetch.app.Busniess.Service.ArticleService;
import com.articlefetch.app.Controller.JacksonModels.Article;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.core.IsNull;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.mockito.BDDMockito.given;

@WebMvcTest(ArticleController.class)
public class ArticleControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private ArticleService service;

    @Test
    void getAllArticles() throws Exception {
        Article a1 = new Article(1, "AutismXYZ", "Joshua",
                "Schappel.com", 1, 1);

        List<Article> articleList = Arrays.asList(a1);

        given(service.getAllArticles()).willReturn(articleList);

        mvc.perform(get("/article/allArticles")
                .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].article_name", is("AutismXYZ")))
                .andExpect(jsonPath("$[0].authors", is("Joshua")))
                .andExpect(jsonPath("$[0].article_site", is("Schappel.com")))
                .andExpect(jsonPath("$[0].fk_account_id", is(1)))
                .andExpect(jsonPath("$[0].fk_categories_id", is(1)));
    }

    @Test
    void getArticle() throws Exception {
        Article a1 = new Article(1, "AutismXYZ", "Joshua",
                "Schappel.com", 1, 1);

        given(service.getArticle(1)).willReturn(a1);

        mvc.perform(get("/article/getArticle?id=1")
                .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$['id']", is(1)))
                .andExpect(jsonPath("$['article_name']", is("AutismXYZ")))
                .andExpect(jsonPath("$['authors']", is("Joshua")))
                .andExpect(jsonPath("$['article_site']", is("Schappel.com")))
                .andExpect(jsonPath("$['fk_account_id']", is(1)))
                .andExpect(jsonPath("$['fk_categories_id']", is(1)));
    }

    @Test
    void getArticle_without_params() throws Exception {
        mvc.perform(get("/article/getArticle")
                .contentType(APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message", is("id parameter is missing")));
    }

    @Test
    void getArticle_with_invalid_id() throws Exception {
        given(service.getArticle(100)).willThrow(new ArticleNotFoundException(100));
        mvc.perform(get("/article/getArticle?id=100")
                .contentType(APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message", is("Article id: 100 is not a valid id")));
    }

    @Test
    void createArticle() throws Exception {
        Article a1 = new Article(1, "AutismXYZ", "Joshua",
                "Schappel.com", 111, 111);

        when(service.createArticle(any())).thenReturn(1);

        mvc.perform(put("/article/create")
                .content(asJsonString(a1))
                .contentType(APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$['id']", is("1")));
    }

    @Test
    void createArticle_with_out_params() throws Exception {
        mvc.perform(put("/article/create")
                .contentType(APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void create_duplicate_article() throws Exception {
        Article a1 = new Article(1, "AutismXYZ", "Joshua",
                "Schappel.com", 23, 21);

        doThrow(DuplicateEntryException.class).when(service).createArticle(a1);

        mvc.perform(put("/article/create")
                .contentType(APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }


    // Helper function that converts a Jackson Object to a json string
    private static String asJsonString(Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
