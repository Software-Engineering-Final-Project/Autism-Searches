package com.articlefetch.app.Controller;


import com.articlefetch.app.Busniess.Service.StarredCategoriesService;
import com.articlefetch.app.Controller.JacksonModels.StarredCategories;
import com.articlefetch.app.Controller.ResponseEntities.SuccessResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * API Routes related to starredCategories
 */

@RestController
@RequestMapping("/starredCategories")
public class StarredCategoriesController {

    @Autowired
    StarredCategoriesService starredCategoriesService;

    @GetMapping("/getAllAStarredCategories")
    public ResponseEntity<List<StarredCategories>> getAllAStarredCategories() {
        return new ResponseEntity<>(starredCategoriesService.getAllStarredCategories(), HttpStatus.OK);
    }

    @GetMapping("/getStarredCategories")
    public ResponseEntity<StarredCategories> getAccount( @RequestParam(value = "id", required = true) Integer id) throws IOException {
        return new ResponseEntity<>(starredCategoriesService.getStarredCategories(id), HttpStatus.OK);
    }

    @PutMapping("/create")
    public ResponseEntity<Map<String, String>> createArticle(@RequestBody StarredCategories categoriesCreate) {
        Integer id = starredCategoriesService.createStarredCategories(categoriesCreate);
        System.out.println(categoriesCreate);
        return SuccessResponseEntity.createdResponseEntity(id);
    }
}
