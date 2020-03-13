package com.articlefetch.app.Controller.JacksonModels;

public class StarredCategories {

    public Integer id;
    public String Starred_categories_name;
    public Integer Fk_account_id;
    public Integer Fk_categories_id;

    public StarredCategories(Integer id, String name, Integer Fk_account_id, Integer Fk_categories_id) {
        this.id = id;
        this.Starred_categories_name = name;
        this.Fk_account_id = Fk_account_id;
        this.Fk_categories_id = Fk_categories_id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStarred_categories_name() {
        return Starred_categories_name;
    }

    public void setStarred_categories_name(String name) {
        this.Starred_categories_name = name;
    }

    public Integer getFk_account_id() {
        return Fk_account_id;
    }

    public Integer getFk_categories_id() {
        return Fk_categories_id;
    }
}






