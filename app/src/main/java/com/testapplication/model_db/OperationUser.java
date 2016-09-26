package com.testapplication.model_db;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.activeandroid.Model;
import com.activeandroid.query.Select;
import com.squareup.picasso.Picasso;
import com.testapplication.R;
import com.testapplication.activity.AddPostFragment;
import com.testapplication.activity.FileChooser;
import com.testapplication.activity.MainActivity;
import com.testapplication.activity.ProfileFragment;
import com.testapplication.activity.UserFragment;
import com.testapplication.tool.Tool;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class OperationUser {
    public static User singUser() {
        return new Select().from(User.class).where("Id = ?", MainActivity.idUser).executeSingle();
    }

    public static void saveProfile(Activity context, FragmentManager fragmentManager, String email, String description, String name, String pathImage) {
        Toast toast;
        if (modifyUser(email, name, pathImage, description)) {
            toast = Toast.makeText(context, "Profile update successful.", Toast.LENGTH_SHORT);
            MainActivity.changeFragment(R.id.container, new UserFragment(), fragmentManager);
            MainActivity.setValuesUserDrawer(context);
        } else {
            toast = Toast.makeText(context, "Profile can not be updated.", Toast.LENGTH_SHORT);
        }
        toast.show();
    }

    public static void showChooser(final Activity activity, final int option) {
        FileChooser chooser = new FileChooser(activity).setFileListener(new FileChooser.FileSelectedListener() {
            @Override
            public void fileSelected(File file) {

                File file1 = Tool.moveFile(file, activity);
                if (file1 != null) {
                    switch (option) {
                        case ProfileFragment.CASE:
                            ProfileFragment.pathImage = file1.getName();
                            ProfileFragment.change(activity, file1);
                            break;
                        case AddPostFragment.CASE:
                            AddPostFragment.pathImage = file1.getName();
                            AddPostFragment.change(activity, file1);
                            break;
                    }
                    // ProfileFragment.pathImage = toPath;
                }
            }
        });
        chooser.setExtension(".png");
        chooser.showDialog();

    }

    public static List<com.testapplication.model_adapter.User> modelToUser(List<User> dataModel) {
        List<com.testapplication.model_adapter.User> datAdapter = new ArrayList<>();
        for (User u : dataModel) {
            com.testapplication.model_adapter.User newUser = new com.testapplication.model_adapter.User(u.name, u.email, u.image, u.description);
            datAdapter.add(newUser);
        }
        return datAdapter;
    }

    public static List<User> allUser() {
        return new Select().from(User.class).execute();
    }

    public static User addUser(final String mEmail, final String mPassword) {
        User newUser = new User();
        newUser.email = mEmail;
        newUser.password = mPassword;
        newUser.status = Status.CREATED.toString();
        newUser.save();
        return newUser;
    }

    public static boolean modifyUser(String email, String name, String imagePath, String description) {
        try {
            User user = singUser();
            user.name = name;
            user.email = email;
            user.description = description;
            user.image = imagePath;
            user.status = Status.MODIFY.toString();
            user.save();
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    public static String findUserByEmailPass(String email, String pass) {
        User user = new Select().from(User.class).where("Email = ?", email).and("Password = ?", pass).executeSingle();
        return user != null ? user.getId().toString() : null;
    }

    public static void loadImage(Context context, ImageView imageView, String name,int w,int h,int errorImagen) {

        try {
            Picasso.with(context).load(new File(MainActivity.directory,name)).resize(w,h).centerCrop().placeholder(errorImagen).error(errorImagen).into(imageView);
        } catch (Exception e) {
            String sm = e.getMessage();
        }
    }

}
