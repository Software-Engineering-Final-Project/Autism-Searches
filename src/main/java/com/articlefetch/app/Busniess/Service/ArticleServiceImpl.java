package com.articlefetch.app.Busniess.Service;

import com.articlefetch.app.Busniess.Exceptions.ArticleNotFoundException;
import com.articlefetch.app.Busniess.Exceptions.DuplicateEntryException;
import com.articlefetch.app.Controller.JacksonModels.Article;
import com.articlefetch.app.DataAccess.ModelDomain.ArticleEntity;
import com.articlefetch.app.DataAccess.Repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


/**
 * This class is responsible for interfacing Hibernate data retrieval API for ArticleEntity
 */
@Service
public class ArticleServiceImpl implements ArticleService{

    @Autowired ArticleRepository articleRepository;


    public ArrayList<Article> searcharticle(Integer id) throws ArticleNotFoundException, IOException{
        HashMap<Integer, ArrayList<Integer>> recomendMap = CsvLoader.csvToMap();
        ArrayList<Integer> ids = recomendMap.get(id);
        System.out.println(Arrays.toString(ids.toArray()));
        ArrayList<Article> arrayList = new ArrayList<Article>();

        for (Integer i : ids){
            ArticleEntity entity = articleRepository.findById(i)
                    .orElseThrow(() -> new ArticleNotFoundException(i));
            arrayList.add(Mapper.from(entity));
        }

        return arrayList;
    }


    @Transactional
    @Override
    public Integer createArticle(Article article) throws DuplicateEntryException {
        // Check if an account exists
        if(!articleRepository.findExistingConflicts(article.article_title, article.authors).isEmpty()) {
            throw new DuplicateEntryException();
        }
        ArticleEntity entity = articleRepository.save(Mapper.from(article));

        return entity.getStaredArticles_id();
    }

    @Override
    public Article getArticle(Integer article_id) throws ArticleNotFoundException, IOException {
        ArticleEntity entity = articleRepository.findById(article_id)
                .orElseThrow(() -> new ArticleNotFoundException(article_id));

        return Mapper.from(entity);
    }

    @Override
    public List<Article> getAllArticles() {
        List<ArticleEntity> list = (List<ArticleEntity>) articleRepository.findAll();
        Stream<Article> stream = list.stream().map( (article) -> Mapper.from(article));
        return stream.collect(Collectors.toList());
    }

    @Transactional
    @Override
    public Article updateArticle(Integer id, Article article) throws ArticleNotFoundException, IOException {
        ArticleEntity entity = articleRepository.findById(id).orElseThrow( () -> new ArticleNotFoundException(id));
        entity.setArticleAuthors(article.getAuthors());
        entity.setArticleTitle(article.getArticle_title());
        entity.setArticleDesc(article.getArticle_desc());

        articleRepository.save(entity);
        return Mapper.from(entity);
    }

}
