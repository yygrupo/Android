package com.testapplication.activity;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.activeandroid.query.Select;
import com.testapplication.R;
import com.testapplication.model_db.User;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends android.app.Fragment {
    private EditText name;
    private EditText email;
    private EditText description;

    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_post, container, false);
        inflater.inflate(R.layout.fragment_profile, container, false);
        name=(EditText) rootView.findViewById(R.id.tvp_user);
        email=(EditText)rootView.findViewById(R.id.tvp_email);
        description=(EditText) rootView.findViewById(R.id.tvp_description);
        User loginUser=singUser();
        if(loginUser.name!=null)
        name.setText(loginUser.name);
        else
        name.setText("");
        if(loginUser.email!=null){
            email.setText(loginUser.email);
        }else{
            email.setText("");
        }
        if (loginUser.description!=null){
            description.setText(loginUser.description);
        }else{
            description.setText("");
        }

        return rootView;
    }
    public User singUser(){
        return new Select().from(User.class).where("Id = ?",MainActivity.idUser).executeSingle();
    }


}
