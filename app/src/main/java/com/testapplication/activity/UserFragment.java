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
import com.testapplication.model_db.OperationUser;
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
        List<User> listUser=OperationUser.allUser();
        List<com.testapplication.model_adapter.User> listUserAdapter = OperationUser.modelToUser(listUser);
        RecyclerView listViewUser= (RecyclerView) rootView.findViewById(R.id.userList);
        listViewUser.setLayoutManager(new LinearLayoutManager(getActivity()));
        UserAdapter userAdapter= new UserAdapter(getActivity(),listUserAdapter);
        listViewUser.setAdapter(userAdapter);
        // Inflate the layout for this fragment
        return rootView;
    }


}
