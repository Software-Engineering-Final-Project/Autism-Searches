package com.articlefetch.app.DataAccess.ModelDomain;


import javax.persistence.*;

@Entity
@Table(name = "categories")
public class CategoryEntity {

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

    public void setId(Integer id){this.id_categories = id;}

    public String getCategoryName(){ return category_name;}

    public void setCategoryName(String cat){ category_name = cat;}

    public String getDescription(){ return category_description;}

    public void setDescription(String desc){ category_description = desc;}
}
