package com.articlefetch.app.Controller.JacksonModels;

public class StarredCategories {

    private Integer fk_account_id;
    private Integer fk_categories_id;

    public StarredCategories(Integer account_id, Integer categories_id) {
        this.fk_account_id = account_id;
        this.fk_categories_id = categories_id;
    }


    public Integer getFk_account_id() {
        return fk_account_id;
    }

    public void setFk_account_id(Integer fk_account_id) {
        this.fk_account_id = fk_account_id;
    }

    public Integer getFk_categories_id() {
        return fk_categories_id;
    }

    public void setFk_categories_id(Integer fk_categories_id) {
        this.fk_categories_id = fk_categories_id;
    }
}






