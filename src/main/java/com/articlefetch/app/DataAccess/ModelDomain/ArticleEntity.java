package com.articlefetch.app.DataAccess.ModelDomain;

import com.articlefetch.app.Busniess.DTO.DTO;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class ArticleEntity implements EntityConvert {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer stared_articles_id;

    private String article_name;

    private String article_authors;

    private String stared_articles;

    private String article_site;

    private Integer fk_account_id;

    @Override
    public String toString() {
        return "ArticleEntity{" +
                "stared_articles_id=" + stared_articles_id +
                ", article_name='" + article_name + '\'' +
                ", article_authors='" + article_authors + '\'' +
                ", stared_articles='" + stared_articles + '\'' +
                ", article_site='" + article_site + '\'' +
                ", fk_account_id='" + fk_account_id + '\'' +
                '}';
    }

    public Integer getStaredarticles_id(){
        return this.stared_articles_id;
    }

    public String getArticlename(){
        return this.article_name;
    }

    public void setArticlename(String article_name) {
        this.article_name = article_name;
    }

    public String getArticleauthors() {
        return this.article_authors;
    }

    public void setArticleauthors(String article_authors){
        this.article_authors = article_authors;
    }

    public String getStarearticles() {
        return this.stared_articles;
    }

    public void setStaredarticles(String stared_articles) {
        this.stared_articles = stared_articles;
    }

    public String getArticlesite(){
        return this.article_site;
    }

    public void setArticlesite(String article_site) {
        this.article_site = article_site;
    }

    public Integer getFK_account_id() { return this.fk_account_id; }

    /**
     * not sure we need this
     */
    public void setFK_account_id() { this.fk_account_id = fk_account_id; }


    @Override
    public DTO entityConvert() {
        return null;
    }
}
