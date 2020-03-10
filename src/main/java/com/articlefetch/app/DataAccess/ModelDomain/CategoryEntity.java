package com.articlefetch.app.DataAccess.ModelDomain;


import com.articlefetch.app.Busniess.DTO.DTO;

import javax.persistence.*;

@javax.persistence.Entity
@Table(name = "Categories")
public class CategoryEntity implements Entity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_categories;

    private String category_name;

    private String category_description;

    @Override
    public String toString() {
        return "CategoryEntity{" +
                "id_categories=" + id_categories +
                ", category_name='" + category_name + '\'' +
                ", category_description='" + category_description + '\'' +
                '}';
    }

    public Integer getId(){ return id_categories;}

    public String getCategories(){ return category_name;}

    public void setCategory(String cat){ category_name = cat;}

    public String getDescription(){ return category_description;}

    public void setDescription(String desc){ category_description = desc;}

    @Override
    public DTO entityConvert() {
        return null;
    }
}
