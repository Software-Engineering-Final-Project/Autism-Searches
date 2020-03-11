package com.articlefetch.app.Busniess.Service;

import com.articlefetch.app.Controller.JacksonModels.Article;
import com.articlefetch.app.DataAccess.ModelDomain.ArticleEntity;
import com.articlefetch.app.DataAccess.Repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * This class is responsible for interfacing Hibernate data retrieval API for ArticleEntity
 */
@Service
public class ArticleServiceImpl implements ArticleService, Conversion<ArticleEntity, Article> {

    @Autowired
    ArticleRepository accountRepository;

    @Override
    public List<Article> allArticles() {
        return null;
    }

    @Override
    public Article getArticle(Integer id) throws ArticleNotFoundException {
        return null;
    }

    @Override
    public ArticleEntity convertToDAO(Article obj) {
        ArticleEntity entity = new ArticleEntity();

        entity.setArticleauthors(obj.authors);
        entity.setArticlename(obj.article_name);
        entity.setArticlesite(obj.article_site);
        entity.setStaredarticles_id(obj.id);
        return entity;
    }

    














}
