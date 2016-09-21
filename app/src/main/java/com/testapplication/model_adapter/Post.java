package com.testapplication.model_adapter;

/**
 * Created by albe on 11-Feb-16.
 */
public class Post {

    private String user;
    private String post_title;
    private int img;
    private String description;

    public Post(String user, String post_title, int img, String description) {
        this.user = user;
        this.post_title = post_title;
        this.img = img;
        this.description = description;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPost_title() {
        return post_title;
    }

    public void setPost_title(String post_title) {
        this.post_title = post_title;
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