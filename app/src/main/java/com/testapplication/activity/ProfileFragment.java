package com.testapplication.activity;


import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.activeandroid.query.Select;
import com.squareup.picasso.Picasso;
import com.testapplication.R;
import com.testapplication.model_db.OperationPost;
import com.testapplication.model_db.OperationUser;
import com.testapplication.model_db.User;

import java.io.File;
import java.io.FileOutputStream;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends android.app.Fragment {
    private EditText name;
    private EditText email;
    private EditText description;
    private Button save;
    private TextView selectImage;
    private static ImageView avatar;
    public static String pathImage;
    public static final int CASE = 1;
    private View rootView;

    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        pathImage = "";

        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_profile, container, false);
        inflater.inflate(R.layout.fragment_profile, container, false);
        name = (EditText) rootView.findViewById(R.id.tvp_user);
        email = (EditText) rootView.findViewById(R.id.tvp_email);
        description = (EditText) rootView.findViewById(R.id.tvp_description);
        save = (Button) rootView.findViewById(R.id.btnp_save);
        avatar = (ImageView) rootView.findViewById(R.id.ivp_avatar);

        selectImage = (TextView) rootView.findViewById(R.id.tv_select_image);
        selectImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OperationUser.showChooser(getActivity(), ProfileFragment.CASE);
            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OperationUser.saveProfile(getActivity(), getFragmentManager(), email.getText().toString(), description.getText().toString(), name.getText().toString(), pathImage);
             //   OperationUser.setValuesUserDrawer();
            }
        });
        setValues();
        return rootView;
    }

    private void setValues() {
        User loginUser = OperationUser.singUser();
        name.setText(loginUser.name);
        email.setText(loginUser.email);
        description.setText(loginUser.description);
        OperationUser.loadImage(getActivity().getApplicationContext(), avatar, loginUser.image);
    }

    public static void change(Activity activity, File file) {

        try {
            Picasso.with(activity.getApplicationContext()).load(file).into(avatar);
        } catch (Exception e) {
            String sm = e.getMessage();
        }
    }
}
