package com.testapplication.model_db;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

import java.util.List;

/**
 * Created by albe on 9/22/2016.
 */
@Table(name = "User")
public class User extends Model {
    @Column(name = "Email")
    public String email;
    @Column(name = "Password")
    public String password;
    @Column(name = "Name")
    public String name;
    @Column(name = "Image")
    public String image;
    @Column(name = "Description")
    public String description;
    public List<Post> getPosts(){
        return getMany(Post.class,"User");
    }

}
