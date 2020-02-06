package com.articlefetch.app.DataAccess.ModelDomain;

import javax.persistence.*;

/**
 * This class creates a entry model for Hibernate so it can mapped to the account table in mysql
 */
@Entity
@Table(name = "Accounts")
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

    @Override
    public String toString() {
        return "AccountEntity{" +
                "account_id=" + id_account +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", email='" + email + '\'' +
                ", status=" + status +
                '}';
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
}
