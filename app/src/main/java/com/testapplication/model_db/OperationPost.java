package com.testapplication.model_db;

import android.app.Activity;
import android.app.FragmentManager;
import android.widget.Toast;

import com.activeandroid.query.Select;
import com.testapplication.R;
import com.testapplication.activity.AddPostFragment;
import com.testapplication.activity.MainActivity;
import com.testapplication.activity.PostFragment;
import com.testapplication.activity.UserFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by albe on 9/23/2016.
 */
public class OperationPost {
    public static void savePost(Activity activity, FragmentManager fragmentManager, final String mtitle, final String mdescription, String pathImage) {
        Toast toast;
        final User muser = OperationUser.singUser();
        if (addPost(mtitle, mdescription, muser, pathImage) != null) {
            toast = Toast.makeText(activity, "Post saved successful.", Toast.LENGTH_SHORT);
              MainActivity.changeFragment(R.id.container, new PostFragment(), fragmentManager);


        } else {
            toast = Toast.makeText(activity, "Post can not be created.", Toast.LENGTH_SHORT);
        }
        toast.show();
    }

    public static Post addPost(final String mtitle, final String mdescription, final User muser, final String pathImage) {
        Post post = new Post();
        post.title = mtitle;
        post.text = mdescription;
        post.user = muser;
        post.image = pathImage;
        post.status = Status.CREATED.toString();
        post.save();
        return post;
    }

    public static boolean modifyPost(int id, final String mtitle, final String mdescription, String pathImage) {
        try {
            Post post = signPost(id);
            post.title = mtitle;
            post.text = mdescription;
            post.status = Status.MODIFY.toString();
            post.image = pathImage;
            post.save();
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    public static Post signPost(int id) {
        return new Select().from(Post.class).where("Id = ?", id).executeSingle();
    }
    public static List<Post> getPosts(Boolean myPost){
        List<Post> posts;
        if(myPost){
            User user= OperationUser.singUser();
            posts= user.getPosts();
        }
        else{
            posts= new Select().from(Post.class).execute();
        }
        return posts;
    }
    public static List<com.testapplication.model_adapter.Post> modelToPost(List<Post> dataModel){
        List<com.testapplication.model_adapter.Post> datAdapter= new ArrayList<>();
        for (Post p:dataModel) {
            com.testapplication.model_adapter.Post newPost=new com.testapplication.model_adapter.Post(p.user.name,p.title,p.image,p.text);
            datAdapter.add(newPost);
        }
        return datAdapter;
    }
}
