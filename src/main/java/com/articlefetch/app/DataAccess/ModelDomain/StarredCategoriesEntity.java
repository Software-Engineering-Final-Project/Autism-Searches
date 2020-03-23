package com.articlefetch.app.DataAccess.ModelDomain;

import javax.persistence.*;

@Entity
@Table(name = "starred_categories")
public class StarredCategoriesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer stared_categories_id;

    private String categories_name;

    @OneToOne(targetEntity = CategoryEntity.class)
    @JoinColumn(name = "id_categories")
    private Integer fk_categories_id;

    @ManyToOne(targetEntity = AccountEntity.class)
    @JoinColumn(name = "id_account")
    private Integer fk_account_id;

    public StarredCategoriesEntity create(Integer id, String name, Integer Fk_account_id, Integer Fk_categories_id) {
        this.stared_categories_id = id;
        this.categories_name =  name;
        this.fk_categories_id = Fk_categories_id;
        this.fk_account_id = Fk_account_id;

        return this;
    }

    @Override
    public String toString() {
        return "ArticleEntity{" +
                "stared_categories_id=" + stared_categories_id +
                ", categories_name='" + categories_name + '\'' +
                ", fk_account_id='" + fk_account_id + '\'' +
                ", fk_categories_id='" + fk_categories_id + '\'' +
                '}';
    }

    public Integer getStarred_categories_id() {
        return this.stared_categories_id;
    }

    public void setStarred_categories_id(Integer id) {
        this.stared_categories_id = id;
    }

    public String getCategories_name() {
        return this.categories_name;
    }

    public void setCategories_name(String name) {
        this.categories_name = name;
    }

    public Integer getFK_account_id() {
        return this.fk_account_id;
    }

    public Integer getFK_categories_id() {
        return this.fk_categories_id;
    }


}