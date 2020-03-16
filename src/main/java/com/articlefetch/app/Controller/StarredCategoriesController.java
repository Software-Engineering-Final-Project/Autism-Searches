package com.articlefetch.app.Controller;


import com.articlefetch.app.Busniess.Service.StarredCategoriesService;
import com.articlefetch.app.Controller.JacksonModels.StarredCategories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
    public ResponseEntity<StarredCategories> getAccount( @RequestParam(value = "id", required = true) Integer id){
        return new ResponseEntity<>(starredCategoriesService.getStarredCategories(id), HttpStatus.OK);
    }
}
