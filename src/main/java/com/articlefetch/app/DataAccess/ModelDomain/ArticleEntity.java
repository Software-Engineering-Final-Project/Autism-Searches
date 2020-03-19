package com.articlefetch.app.DataAccess.ModelDomain;

import javax.persistence.*;


@Entity
@Table(name = "starred_articles")
public class ArticleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer stared_articles_id;

    private String article_name;

    private String article_authors;

    private String article_site;

    @ManyToOne(targetEntity = CategoryEntity.class)
    @JoinColumn(name="id_categories")
    private Integer fk_categories_id;

    @ManyToOne(targetEntity = AccountEntity.class)
    @JoinColumn(name="id_account")
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

    public ArticleEntity create(Integer id, String article_name, String authors, String article_site,
                                Integer Fk_account_id, Integer Fk_categories_id) {

        this.stared_articles_id = id;
        this.article_name = article_name;
        this.article_authors = authors;
        this.article_site = article_site;
        this.fk_account_id = Fk_account_id;
        this.fk_categories_id = Fk_categories_id;

        return this;
    }

    public Integer getStaredArticles_id(){
        return this.stared_articles_id;
    }

    public void setStaredArticles_id(Integer stared_articles_id){
         this.stared_articles_id = stared_articles_id;
    }

    public String getArticleName(){
        return this.article_name;
    }

    public void setArticleName(String article_name) {
        this.article_name = article_name;
    }

    public String getArticleAuthors() {
        return this.article_authors;
    }

    public void setArticleAuthors(String article_authors){
        this.article_authors = article_authors;
    }

    public String getArticleSite(){
        return this.article_site;
    }

    public void setArticleSite(String article_site) {
        this.article_site = article_site;
    }

    public Integer getFK_account_id() { return this.fk_account_id; }

    public Integer getFK_categories_id() { return this.fk_categories_id; }

}
