package com.articlefetch.app.Controller;


import com.articlefetch.app.Busniess.Exceptions.DuplicateEntryException;
import com.articlefetch.app.Busniess.Service.ArticleService;
import com.articlefetch.app.Controller.JacksonModels.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
    public ResponseEntity<Article> getAccount( @RequestParam(value = "id", required = true) Integer id){
        return new ResponseEntity<>(articleService.getArticle(id), HttpStatus.OK);
    }

    //TODO: add article endpoint

}
