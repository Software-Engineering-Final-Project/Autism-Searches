package com.articlefetch.app.DataAccess.ModelDomain;

import javax.persistence.*;


@Entity
@Table(name = "articles")
public class ArticleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer stared_articles_id;

    private String article_name;

    private String article_authors;

    private String article_site;

    private Integer fk_categories_id;

    private Integer fk_account_id;

    @Override
    public String toString() {
        return "ArticleEntity{" +
                "stared_articles_id=" + stared_articles_id +
                ", article_name='" + article_name + '\'' +
                ", article_authors='" + article_authors + '\'' +
                ", article_site='" + article_site + '\'' +
                ", fk_account_id='" + fk_account_id + '\'' +
                ", fk_categories_id='" + fk_categories_id + '\'' +
                '}';
    }

    public Integer getStaredarticles_id(){
        return this.stared_articles_id;
    }

    public void setStaredarticles_id(Integer stared_articles_id){
         this.stared_articles_id = stared_articles_id;
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

    public String getArticlesite(){
        return this.article_site;
    }

    public void setArticlesite(String article_site) {
        this.article_site = article_site;
    }

    public Integer getFK_account_id() { return this.fk_account_id; }

    public Integer getFK_categories_id() { return this.fk_categories_id; }

}
