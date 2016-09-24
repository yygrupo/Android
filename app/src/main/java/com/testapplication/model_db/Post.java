package com.testapplication.model_db;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

@Table(name = "Post")
public class Post extends Model {
    @Column(name = "Title")
    public String title;

    @Column(name = "Image")
    public String image;

    @Column(name = "Text")
    public String text;

    @Column(name = "User")
    public User user;

    @Column(name = "Status")
    public String status;
}
