package com.articlefetch.app.Controller.JacksonModels;

public class Article {

    public Integer id;
    public String article_name;
    public String authors;
    public String article_site;
    public Integer Fk_account_id;
    public Integer Fk_categories_id;

    public Article(Integer id, String article_name, String authors, String article_site, Integer Fk_account_id, Integer Fk_categories_id) {
        this.id = id;
        this.article_name = article_name;
        this.authors = authors;
        this.article_site = article_site;
        this.Fk_account_id = Fk_account_id;
        this.Fk_categories_id = Fk_categories_id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getFk_account_id() {
        return Fk_account_id;
    }

    public Integer getFk_categories_id() {
        return Fk_categories_id;
    }

    public String getArticle_name() {
        return article_name;
    }

    public String getArticle_site() {
        return article_site;
    }

    public String getAuthors() {
        return authors;
    }

    public void setArticle_name(String article_name) {
        this.article_name = article_name;
    }

    public void setArticle_site(String article_site) {
        this.article_site = article_site;
    }

    public void setAuthors(String authors) {
        this.authors = authors;
    }

}
