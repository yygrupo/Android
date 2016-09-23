package com.testapplication.model_adapter;

public class User {
    private String user;
    private String email;
    private String img;
    private String description;

    public User(String user, String email,String img, String description) {
        this.user=user;
        this.email=email;
        this.img=img;
        this.description=description;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }



}