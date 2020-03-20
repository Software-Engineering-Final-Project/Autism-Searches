package com.articlefetch.app.Controller;

import com.articlefetch.app.Busniess.Service.AccountService;
import com.articlefetch.app.Busniess.Service.ImageService;
import com.articlefetch.app.Controller.JacksonModels.Image;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ImageController.class)
class ImageControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private ImageService service;

    @Test
    void getAllImages() throws Exception {
        byte[] array1 = new byte[]{1,2,3};
        byte[] array2 = new byte[]{1,2,3};

        List<Image> imageList = new ArrayList<>();
        imageList.add(new Image("/test", array1));
        imageList.add(new Image("/test2", array2));

        given(service.getAllImages()).willReturn(imageList);

        mvc.perform(get("/image/all")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].path", is("/test")))
                .andExpect(jsonPath("$[0].image", is("AQID")))
                .andExpect(jsonPath("$[1].path", is("/test2")))
                .andExpect(jsonPath("$[1].image", is("AQID")));
    }

    @Test
    void getAllImages_when_there_are_not_any_images() throws Exception {
        List<Image> imageList = new ArrayList<>();

        given(service.getAllImages()).willReturn(imageList);

        mvc.perform(get("/image/all")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
    }
}