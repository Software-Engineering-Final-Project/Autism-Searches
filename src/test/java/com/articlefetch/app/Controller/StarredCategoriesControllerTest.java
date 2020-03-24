package com.articlefetch.app.Controller;

import com.articlefetch.app.Busniess.Exceptions.DuplicateEntryException;
import com.articlefetch.app.Busniess.Service.StarredCategoriesService;
import com.articlefetch.app.Controller.JacksonModels.StarredCategories;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.core.IsNull;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
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

@WebMvcTest(StarredCategoriesController.class)
public class StarredCategoriesControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private StarredCategoriesService service;

    @Test
    void getAllStarredCategories() throws Exception {
        StarredCategories c1 = new StarredCategories(1, "Research", 11,
                 11);

        List<StarredCategories> starredCategoriesList = Arrays.asList(c1);

        given(service.getAllStarredCategories()).willReturn(starredCategoriesList);

        mvc.perform(get("/starredCategories/getAllStarredCategories")
                .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].Starred_categories_name", is("Research")))
                .andExpect(jsonPath("$[0].fk_account_id", is(11)))
                .andExpect(jsonPath("$[0].fk_categories_id", is(11)));
    }

    @Test
    void getStarredCategories() throws Exception {
        StarredCategories c1 = new StarredCategories(1, "Research", 10,
                10);

        given(service.getStarredCategories(1)).willReturn(c1);

        mvc.perform(get("/starredCategories/getStarredCategories?id=1")
                .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$['id']", is(1)))
                .andExpect(jsonPath("$['Starred_categories_name']", is("Research")))
                .andExpect(jsonPath("$['fk_account_id']", is(10)))
                .andExpect(jsonPath("$['fk_categories_id']", is(10)));
    }

    @Test
    void createStarredCategories() throws Exception {
        StarredCategories c1 = new StarredCategories(1, "Research", 10,
                10);
        StarredCategories c2 = new StarredCategories(1, "Science", 10,
                10);
        List<StarredCategories> list = new ArrayList<>();
        list.add(c1);
        list.add(c2);

        when(service.createStarredCategories(any())).thenReturn(2);

        mvc.perform(put("/starredCategories/create")
                .content(asJsonString(list))
                .contentType(APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$['rows']", is("2")));
    }

    @Test
    void createStarredCategories_with_out_params() throws Exception {
        mvc.perform(put("/starredCategories/create")
                .contentType(APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void create_duplicate_starredCategories() throws Exception {
        StarredCategories c1 = new StarredCategories(1, "Research", 10,
                10);
        StarredCategories c2 = new StarredCategories(1, "Science", 10,
                10);
        List<StarredCategories> list = new ArrayList<>();
        list.add(c1);
        list.add(c2);

        doThrow(DuplicateEntryException.class).when(service).createStarredCategories(list);

        mvc.perform(put("/starredCategories/create")
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
