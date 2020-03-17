package com.articlefetch.app.Busniess.Service;

import com.articlefetch.app.Busniess.Exceptions.ArticleNotFoundException;
import com.articlefetch.app.Busniess.Exceptions.DuplicateEntryException;
import com.articlefetch.app.Controller.JacksonModels.Article;
import com.articlefetch.app.DataAccess.ModelDomain.ArticleEntity;
import com.articlefetch.app.DataAccess.Repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


/**
 * This class is responsible for interfacing Hibernate data retrieval API for ArticleEntity
 */
@Service
public class ArticleServiceImpl implements ArticleService, Conversion<ArticleEntity, Article> {

    @Autowired ArticleRepository articleRepository;

    @Transactional
    @Override
    public void createArticle(Article article) throws DuplicateEntryException {
        // Check if an account exists
        if(!articleRepository.findExistingConflicts(article.article_name, article.article_site).isEmpty()) {
            throw new DuplicateEntryException();
        }
        articleRepository.save(convertToDAO(article));
    }

    @Override
    public Article getArticle(Integer article_id) throws ArticleNotFoundException {
        return convertToJackson( articleRepository.findById(article_id)
                .orElseThrow(() -> new ArticleNotFoundException(article_id)));
    }

    @Override
    public List<Article> getAllArticles() {
        List<ArticleEntity> list = (List<ArticleEntity>) articleRepository.findAll();
        Stream<Article> stream = list.stream().map( (account) -> convertToJackson(account));
        return stream.collect(Collectors.toList());
    }

    @Override
    public Article updateArticle(Integer id, Article article) throws ArticleNotFoundException {
        ArticleEntity entity = articleRepository.findById(id).orElseThrow( () -> new ArticleNotFoundException(id));
        entity.setArticleAuthors(article.authors);
        entity.setArticleName(article.article_name);
        entity.setArticleSite(article.article_site);

        articleRepository.save(entity);
        return convertToJackson(entity);
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
