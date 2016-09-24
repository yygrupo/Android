package com.testapplication.activity;


import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.activeandroid.query.Select;
import com.testapplication.R;
import com.testapplication.adapters.PostAdapter;
import com.testapplication.adapters.UserAdapter;
import com.testapplication.model_db.OperationPost;
import com.testapplication.model_db.Post;
import com.testapplication.model_db.User;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class PostFragment extends android.app.Fragment {
    public   int CASE = 1;
    private FloatingActionButton fab;
    public PostFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView =  inflater.inflate(R.layout.fragment_post, container, false);
        fab=(FloatingActionButton) rootView.findViewById(R.id.fab);
        List<Post> listPost= OperationPost.getPosts(MainActivity.myPosts);
        List<com.testapplication.model_adapter.Post> listPostAdapter = OperationPost.modelToPost(listPost);
        RecyclerView listViewUser= (RecyclerView) rootView.findViewById(R.id.postList);
        listViewUser.setLayoutManager(new LinearLayoutManager(getActivity()));
        PostAdapter postAdapter= new PostAdapter(getActivity(),listPostAdapter);
        listViewUser.setAdapter(postAdapter);
        // Inflate the layout for this fragment
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.changeFragment(R.id.container,new AddPostFragment(),getFragmentManager());

            }
        });
        return rootView;
    }



}
