package com.testapplication.activity;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.activeandroid.query.Select;
import com.testapplication.R;
import com.testapplication.adapters.UserAdapter;
import com.testapplication.model_db.User;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class UserFragment extends android.app.Fragment {


    public UserFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_user, container, false);
        List<User> listUser=new Select().from(User.class).execute();
        List<com.testapplication.model_adapter.User> listUserAdapter = modelToUser(listUser);
        RecyclerView listViewUser= (RecyclerView) rootView.findViewById(R.id.userList);
        listViewUser.setLayoutManager(new LinearLayoutManager(getActivity()));
        UserAdapter userAdapter= new UserAdapter(getActivity(),listUserAdapter);
        listViewUser.setAdapter(userAdapter);
        // Inflate the layout for this fragment
        return rootView;
    }
    private List<com.testapplication.model_adapter.User> modelToUser(List<User> dataModel){
        List<com.testapplication.model_adapter.User> datAdapter= new ArrayList<>();
        for (User u:dataModel) {
            com.testapplication.model_adapter.User newUser=new com.testapplication.model_adapter.User(u.name,u.email,u.image,u.description);
            datAdapter.add(newUser);
        }
        return datAdapter;
    }

}
