package com.testapplication.model_adapter;

public class User {
    private String user;
    private String email;
    private int img;
    private String description;

    public User(String user, String email,int img, String description) {
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

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }



}