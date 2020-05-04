package com.articlefetch.app.DataAccess.ModelDomain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "article")
public class ArticleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id_article;

    private String title;

    private String author;

    private String descrip;

    private String url;

    @ManyToMany(cascade = { CascadeType.MERGE }, mappedBy = "articles")
    private Set<AccountEntity> accounts = new HashSet<>();


//    @ManyToOne(targetEntity = CategoryEntity.class)
//    @JoinColumn(name="id_categories")
    private Integer fk_category_id;

    @Override
    public String toString() {
        return "ArticleEntity{" +
                "id_article=" + id_article +
                ", descrip='" + descrip + '\'' +
                ", author='" + author + '\'' +
                ", title='" + title + '\'' +
                ", fk_category_id='" + fk_category_id + '\'' +
                '}';
    }

    public ArticleEntity create(Integer id, String descrip,
                                String authors, String title, Integer fk_category_id, String url) {
        this.id_article = id;
        this.descrip = descrip;
        this.author = authors;
        this.title = title;
        this.url = url;
        this.fk_category_id = fk_category_id;
        return this;
    }

    public Integer getStaredArticles_id(){
        return this.id_article;
    }

    public void setStaredArticles_id(Integer id_article){
         this.id_article = id_article;
    }

    public String getArticleDesc(){
        return this.descrip;
    }

    public void setArticleDesc(String descrip) {
        this.descrip = descrip;
    }

    public String getArticleTitle(){
        return this.title;
    }

    public void setArticleTitle(String title) {
        this.title = title;
    }

    public String getArticleAuthors() {
        return this.author;
    }

    public void setArticleAuthors(String author){
        this.author = author;
    }

    public Integer getfk_category_id() { return this.fk_category_id; }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
