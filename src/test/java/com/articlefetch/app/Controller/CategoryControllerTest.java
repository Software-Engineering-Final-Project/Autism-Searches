package com.articlefetch.app.Controller;


import com.articlefetch.app.Busniess.Exceptions.CategoryNotFoundException;
import com.articlefetch.app.Busniess.Service.CategoryService;
import com.articlefetch.app.Controller.JacksonModels.Category;
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
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(CategoryController.class)
public class CategoryControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private CategoryService service;

    @Test
    void getAllCategories() throws Exception {
        Category a1 = new Category(null, "Childhood Autism", "Bla Bla Bla");

        List<Category> categoriesList = Arrays.asList(a1);

        given(service.getAllCategories()).willReturn(categoriesList);

        mvc.perform(get("/category/allCategories")
                .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", IsNull.nullValue()))
                .andExpect(jsonPath("$[0].name", is("Childhood Autism")))
                .andExpect(jsonPath("$[0].description", is("Bla Bla Bla")));

    }

    @Test
    void getCategory() throws Exception {
        Category a1 = new Category(1, "Childhood Autism", "Bla Bla Bla");

        given(service.getCategory(1)).willReturn(a1);

        mvc.perform(get("/category/getCategory?id=1")
                .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$['id']", is(1)))
                .andExpect(jsonPath("$['name']", is("Childhood Autism")))
                .andExpect(jsonPath("$['description']", is("Bla Bla Bla")));

    }

    @Test
    void getCategory_without_params() throws Exception {
        mvc.perform(get("/category/getCategory")
                .contentType(APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message", is("id parameter is missing")));
    }

    @Test
    void getCategory_with_invalid_id() throws Exception {
        given(service.getCategory(100)).willThrow(new CategoryNotFoundException(100));
        mvc.perform(get("/category/getCategory?id=100")
                .contentType(APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message", is("Category id: 100 is not a valid id")));
    }

}
