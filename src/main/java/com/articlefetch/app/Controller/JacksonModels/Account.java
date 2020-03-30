package com.articlefetch.app.Controller.JacksonModels;

public class Account {

    private Integer id;
    private String username;
    private String first_name;
    private String last_name;
    private String email;
    private String path;
    private byte[] image;
    private boolean status;

    public Account(String username, String first_name, String last_name, String email, Integer id,
                         byte[] image, String path, boolean status) {
        this.email = email;
        this.first_name = first_name;
        this.last_name = last_name;
        this.username = username;
        this.id = id;
        this.image = image;
        this.path = path;
        this.status = status;
    }




    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
