package com.articlefetch.app.Controller;


import com.articlefetch.app.Busniess.Exceptions.DuplicateEntryException;
import com.articlefetch.app.Busniess.Service.ArticleService;
import com.articlefetch.app.Controller.JacksonModels.Article;
import com.articlefetch.app.Controller.ResponseEntities.SuccessResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * API Routes related to article
 */

@RestController
@RequestMapping("/article")
public class ArticleController {

    @Autowired ArticleService articleService;

    @GetMapping("/allArticles")
    public ResponseEntity<List<Article>> getAllAccounts() {
        return new ResponseEntity<>(articleService.getAllArticles(), HttpStatus.OK);
    }

    @GetMapping("/getArticle")
    public ResponseEntity<Article> getAccount( @RequestParam(value = "id", required = true) Integer id) throws IOException {
        return new ResponseEntity<>(articleService.getArticle(id), HttpStatus.OK);
    }

    @PutMapping("/create")
    public ResponseEntity<Map<String, String>> createArticle(@RequestBody Article articleCreate) {
        Integer id = articleService.createArticle(articleCreate);
        System.out.println(articleCreate);
        return SuccessResponseEntity.createdResponseEntity(id);
    }

}
