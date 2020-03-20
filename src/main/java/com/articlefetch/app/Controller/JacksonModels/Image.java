package com.articlefetch.app.Controller.JacksonModels;

public class Image {
    private String path;
    private byte[] image;

    public Image(String path, byte[] image) {
        this.path = path;
        this.image = image;
    }


    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}
