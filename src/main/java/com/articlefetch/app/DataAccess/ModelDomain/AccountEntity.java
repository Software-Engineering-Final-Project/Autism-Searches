package com.articlefetch.app.DataAccess.ModelDomain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * This class creates a entry model for Hibernate so it can mapped to the account table in mysql
 */
@Entity
@Table(name = "accounts")
public class AccountEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_account;

    private String username;

    private String password;

    private String first_name;

    private String last_name;

    private String email;

    private Integer status;

    private String path;

    @ManyToMany(cascade = { CascadeType.MERGE })
    @JoinTable(
            name = "accounts_categories",
            joinColumns = { @JoinColumn(name ="id_account") },
            inverseJoinColumns = { @JoinColumn(name = "id_categories")}
    )
    private Set<CategoryEntity> categories = new HashSet<>();

    @ManyToMany(cascade = { CascadeType.MERGE })
    @JoinTable(
            name = "accounts_categories",
            joinColumns = { @JoinColumn(name ="account_id") },
            inverseJoinColumns = { @JoinColumn(name = "article_id")}
    )
    private Set<CategoryEntity> articles = new HashSet<>();


    @Override
    public String toString() {
        return "AccountEntity{" +
                "id_account=" + id_account +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", email='" + email + '\'' +
                ", status=" + status +
                ", path='" + path + '\'' +
                '}';
    }

    public AccountEntity create(Integer id, String first_name, String last_name, String username, String password,
                                String email, String path, boolean status) {
        this.id_account = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.username = username;
        this.password = password;
        this.email = email;
        this.path = path;
        this.status = status ? 1 : 0;
        return this;
    }

    // Checks if a category is already in the many to many relationship
    public boolean isPresent(CategoryEntity entity) {
        return this.categories.stream().anyMatch( (item) -> item.getId().equals(entity.getId()));
    }

    // Checks if a category is already in the many to many relationship
    public Optional<CategoryEntity> getCategory(CategoryEntity entity) {
       return this.categories.stream()
                .filter( (item) -> item.getId().equals(entity.getId()))
                .findFirst();
    }

    public Integer getAccount_id(){
        return this.id_account;
    }

    public String getUsername(){
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public String getFirst_name() {
        return this.first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name(){
        return this.last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getEmail(){
        return this.email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public boolean getStatus() {
        return this.status == 1; // if 1 then True
    }

    public void setStatus(boolean status){
        this.status = status ? 1 : 0;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setId_account(Integer id_account) {
        this.id_account = id_account;
    }

    public Set<CategoryEntity> getCategories() {
        return categories;
    }

    public void setCategories(Set<CategoryEntity> categories) {
        this.categories = categories;
    }
}
