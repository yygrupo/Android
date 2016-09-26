package com.testapplication.activity;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.activeandroid.query.Select;
import com.squareup.picasso.Picasso;
import com.testapplication.R;
import com.testapplication.model_db.OperationPost;
import com.testapplication.model_db.OperationUser;
import com.testapplication.model_db.Post;
import com.testapplication.model_db.User;

import java.io.File;


/**
 * A simple {@link Fragment} subclass.
 */
public class AddPostFragment extends android.app.Fragment {
    private TextView title;
    private TextView description;
    private Button save;
    public static final int CASE = 2;
    public static String pathImage;
    private static ImageView avatar;
    private TextView selectImage;

    public AddPostFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_add_post, container, false);
        title = (TextView) rootView.findViewById(R.id.tvadd_title);
        description = (TextView) rootView.findViewById(R.id.tvadd_description);
        avatar = (ImageView) rootView.findViewById(R.id.ivadd_picture);
        selectImage = (TextView) rootView.findViewById(R.id.post_select_img);
        selectImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OperationUser.showChooser(getActivity(), AddPostFragment.CASE);
            }
        });
        save = (Button) rootView.findViewById(R.id.btnadd_save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OperationPost.savePost(getActivity(), getFragmentManager(), title.getText().toString(), description.getText().toString(), pathImage);
            }
        });
        return rootView;
    }

    public static void change(Activity activity, File file) {
        try {
            Picasso.with(activity.getApplicationContext()).load(file).into(avatar);
        } catch (Exception e) {
            String sm = e.getMessage();
        }
    }
}
