package com.testapplication.activity;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.activeandroid.query.Select;
import com.testapplication.R;
import com.testapplication.model_db.User;

import java.io.File;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends android.app.Fragment {
    private EditText name;
    private EditText email;
    private EditText description;
    private Button save;
    private TextView selectImage;
    private String pathImage;

    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_profile, container, false);
        inflater.inflate(R.layout.fragment_profile, container, false);
        name=(EditText) rootView.findViewById(R.id.tvp_user);
        email=(EditText)rootView.findViewById(R.id.tvp_email);
        description=(EditText) rootView.findViewById(R.id.tvp_description);
        save=(Button) rootView.findViewById(R.id.btnp_save) ;
        selectImage=(TextView)rootView.findViewById(R.id.tv_select_image) ;
        selectImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showChooser();
            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveProfile();
            }
        });
        setValues();
        return rootView;
    }
    public User singUser(){
        return new Select().from(User.class).where("Id = ?",MainActivity.idUser).executeSingle();
    }
    private void setValues(){
        User loginUser=singUser();
        name.setText(loginUser.name);
        email.setText(loginUser.email);
        description.setText(loginUser.description);
    }
    public void saveProfile(){
        User loginUser=singUser();
        loginUser.email=email.getText().toString();
        loginUser.description=description.getText().toString();
        loginUser.name=name.getText().toString();
        try{
            loginUser.save();
            Toast toast= Toast.makeText(getActivity(),"Profile update successful.",Toast.LENGTH_SHORT);
            toast.show();
            MainActivity.changeFragment(R.id.container,new UserFragment(),getFragmentManager());
        }catch (Exception e){

        }



    }
    public void showChooser(){

        new FileChooser(getActivity()).setFileListener(new FileChooser.FileSelectedListener() {
            @Override
            public void fileSelected(File file) {
                pathImage=  file.getAbsolutePath().toString();
            }
        }).showDialog();

    }


}
