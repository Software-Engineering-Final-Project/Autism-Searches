package com.articlefetch.app.Controller;

import com.articlefetch.app.Busniess.Service.StarredCategoriesService;
import com.articlefetch.app.Controller.JacksonModels.StarredCategories;
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
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.mockito.BDDMockito.given;

@WebMvcTest(StarredCategoriesController.class)
public class StarredCategoriesControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private StarredCategoriesService service;

    @Test
    void getAllStarredCategories() throws Exception {
        StarredCategories c1 = new StarredCategories(1, "Research", null,
                 null);

        List<StarredCategories> starredCategoriesList = Arrays.asList(c1);

        given(service.getAllStarredCategories()).willReturn(starredCategoriesList);

        mvc.perform(get("/starredCategories/AllStarredCategories")
                .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].article_name", is("Research")))
                .andExpect(jsonPath("$[0].fk_account_id", IsNull.nullValue()))
                .andExpect(jsonPath("$[0].fk_categories_id", IsNull.nullValue()));
    }









}
