package com.articlefetch.app.Controller.JacksonModels;

import com.articlefetch.app.Busniess.DTO.AccountDTO;
import com.articlefetch.app.Busniess.DTO.DTO;

public class Account implements JacksonObject {

    public Integer id;
    public String username;
    public String password;
    public String first_name;
    public String last_name;
    public String email;
    public String path;
    public boolean status;

    public Account(String username, String password, String first_name, String last_name, String email, Integer id,
                   String path, boolean status) {
        this.email = email;
        this.first_name = first_name;
        this.last_name = last_name;
        this.password = password;
        this.username = username;
        this.id = id;
        this.path = path;
        this.status = status;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean bool){
        this.status = bool;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public DTO convert(JacksonObject j) {
        return new AccountDTO(username, password, first_name, last_name, email, id, path, status);
    }
}
