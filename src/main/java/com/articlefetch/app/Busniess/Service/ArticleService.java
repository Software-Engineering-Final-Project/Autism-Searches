package com.articlefetch.app.Busniess.Service;

import com.articlefetch.app.Controller.JacksonModels.Article;

import java.util.List;

public interface ArticleService {
    public List<Article> allArticles();
    public Article getArticle(Integer id)  throws ArticleNotFoundException;
}
