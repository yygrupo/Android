package com.testapplication.activity;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.activeandroid.query.Select;
import com.testapplication.R;
import com.testapplication.model_db.Post;
import com.testapplication.model_db.User;


/**
 * A simple {@link Fragment} subclass.
 */
public class AddPostFragment extends android.app.Fragment {
    private TextView title;
    private TextView description;
    private Button save;

    public AddPostFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         View rootView =  inflater.inflate(R.layout.fragment_add_post, container, false);
         title = (TextView) rootView.findViewById(R.id.tvadd_title);
        description=(TextView) rootView.findViewById(R.id.tvadd_description);
        save=(Button) rootView.findViewById(R.id.btnadd_save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                savePost(title.getText().toString(),description.getText().toString());
            }
        });
        return rootView;
    }
    public void  savePost(String title,String description){
        User user= (User) new Select().from(User.class).where("Id = ?",MainActivity.idUser).executeSingle();
        Post post= new Post();
        post.title=title;
        post.text=description;
        post.user=user;
        post.save();
        Toast toast = Toast.makeText(getActivity(), "Post saved succesfull", Toast.LENGTH_SHORT);
        toast.show();
        MainActivity.changeFragment(R.id.container,new PostFragment(),getFragmentManager());


    }

}
