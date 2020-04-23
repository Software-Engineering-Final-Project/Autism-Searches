package com.articlefetch.app.DataAccess.ModelDomain;

import javax.persistence.*;


@Entity
@Table(name = "starred_articles")
public class ArticleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer stared_articles_id;

    private String article_authors;

    private String description;

    @ManyToOne(targetEntity = CategoryEntity.class)
    @JoinColumn(name="id_categories")
    private Integer fk_categories_id;

    @Override
    public String toString() {
        return "ArticleEntity{" +
                "stared_articles_id=" + stared_articles_id +
                ", description='" + description + '\'' +
                ", article_authors='" + article_authors + '\'' +
                ", fk_categories_id='" + fk_categories_id + '\'' +
                '}';
    }

    public ArticleEntity create(Integer id, String description,
                                String authors, Integer Fk_categories_id) {
        this.stared_articles_id = id;
        this.description = description;
        this.article_authors = authors;
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
        return this.description;
    }

    public void setArticleName(String description) {
        this.description = description;
    }

    public String getArticleAuthors() {
        return this.article_authors;
    }

    public void setArticleAuthors(String article_authors){
        this.article_authors = article_authors;
    }

    public Integer getFK_categories_id() { return this.fk_categories_id; }

}
