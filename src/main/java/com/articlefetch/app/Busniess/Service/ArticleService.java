package com.articlefetch.app.Busniess.Service;

import com.articlefetch.app.Busniess.Exceptions.ArticleNotFoundException;
import com.articlefetch.app.Controller.JacksonModels.Article;

import java.util.List;

public interface ArticleService {
    public List<Article> getAllArticles();
    public Article getArticle(Integer id)  throws ArticleNotFoundException;
    public Article updateArticle(Integer id, Article account) throws ArticleNotFoundException;

}
