package com.articlefetch.app.Busniess.Service;

import com.articlefetch.app.Busniess.Exceptions.ArticleNotFoundException;
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

        entity.setArticleAuthors(obj.authors);
        entity.setArticleName(obj.article_name);
        entity.setArticleSite(obj.article_site);
        entity.setStaredArticles_id(obj.id);
        return entity;
    }

    @Override
    public Article convertToJackson(ArticleEntity obj) {
        return new Article(obj.getStaredArticles_id(),
                obj.getArticleName(),
                obj.getArticleAuthors(),
                obj.getArticleSite(),
                obj.getFK_account_id(),
                obj.getFK_categories_id());
    }
}
