package com.articlefetch.app.Controller;


import com.articlefetch.app.Busniess.Service.CategoryService;
import com.articlefetch.app.Controller.JacksonModels.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * API Routes related to category
 */

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired CategoryService categoryService;

    @GetMapping("/allCategories")
    public ResponseEntity<List<Category>> getAllCategories() {
        return new ResponseEntity<>(categoryService.getAllCategories(), HttpStatus.OK);
    }

    @GetMapping("/getCategory")
    public ResponseEntity<Category> getCategory( @RequestParam(value = "id", required = true) Integer id){
        return new ResponseEntity<>(categoryService.getCategory(id), HttpStatus.OK);
    }


}
