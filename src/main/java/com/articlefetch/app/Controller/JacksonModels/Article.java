package com.articlefetch.app.Controller.JacksonModels;

public class Article {

    public Integer id;
    public String article_title;
    public String authors;
    public String article_desc;
    public Integer Fk_categories_id;

    public Article(Integer id, String article_title, String authors, String article_desc, Integer Fk_categories_id) {
        this.id = id;
        this.article_title = article_title;
        this.authors = authors;
        this.article_desc = article_desc;
        this.Fk_categories_id = Fk_categories_id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getFk_categories_id() {
        return Fk_categories_id;
    }

    public String getArticle_title() {
        return article_title;
    }

    public String getArticle_desc() {
        return article_desc;
    }

    public String getAuthors() {
        return authors;
    }

    public void setArticle_title(String article_title) {
        this.article_title = article_title;
    }

    public void setArticle_desc(String article_desc) {
        this.article_desc = article_desc;
    }

    public void setAuthors(String authors) {
        this.authors = authors;
    }

}
