package com.articlefetch.app.DataAccess.ModelDomain;


import com.articlefetch.app.Controller.JacksonModels.Account;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Entity
@Table(name = "categories")
public class CategoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_categories;

    @Column(unique = true)
    private String category_name;

    private String category_description;

    @ManyToMany(cascade = { CascadeType.MERGE })
    @JoinTable(
            name = "accounts_categories",
            joinColumns = { @JoinColumn(name ="id_account") },
            inverseJoinColumns = { @JoinColumn(name = "id_categories")}
    )
    private Set<AccountEntity> accounts = new HashSet<>();


    @Override
    public String toString() {
        return "CategoryEntity{" +
                "id_categories=" + id_categories +
                ", category_name='" + category_name + '\'' +
                ", category_description='" + category_description + '\'' +
                '}';
    }

    public CategoryEntity create(Integer id, String category_name, String description) {

        this.id_categories = id;
        this.category_name = category_name;
        this.category_description = description;

        return this;
    }

    // Checks if a account is already in the many to many relationship
    public boolean isPresent(AccountEntity entity) {
        return this.accounts.stream().anyMatch( (item) -> item.getAccount_id().equals(entity.getAccount_id()));
    }

    // Checks if a account is already in the many to many relationship
    public Optional<AccountEntity> getAccount(AccountEntity entity) {
        return this.accounts.stream()
                .filter( (item) -> item.getAccount_id().equals(entity.getAccount_id()))
                .findFirst();
    }


    public Integer getId(){ return id_categories;}

    public void setId(Integer id){this.id_categories = id;}

    public String getCategoryName(){ return category_name;}

    public void setCategoryName(String cat){ category_name = cat;}

    public String getDescription(){ return category_description;}

    public void setDescription(String desc){ category_description = desc;}

    public Set<AccountEntity> getAccounts() {
        return accounts;
    }

    public void setAccounts(Set<AccountEntity> accounts) {
        this.accounts = accounts;
    }
}
