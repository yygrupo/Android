package com.testapplication.activity;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.testapplication.R;
import com.testapplication.model_db.OperationUser;
import com.testapplication.model_db.User;

import java.io.File;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    public static int idUser;
    public static boolean myPosts;
    public static String directory;
    public static ImageView drawerAvatar;
    public static TextView drawerName;
    public static TextView drawerEmail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        directory= getExternalFilesDir(Environment.DIRECTORY_PICTURES).getPath();
        idUser = Integer.parseInt(getIntent().getStringExtra("id"));

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle(R.string.app_name);
        UserFragment fragment = new UserFragment();
        changeFragment(R.id.container, fragment, getFragmentManager());

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        drawer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    drawerAvatar=(ImageView) findViewById(R.id.drawerAvatar);
                    drawerName=(TextView) findViewById(R.id.drawerName);
                    drawerEmail=(TextView) findViewById(R.id.drawerEmail);
                    setValuesUserDrawer(getApplicationContext());

            }
        });

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.logout) {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();


        if (id == R.id.users_menu) {
            changeFragment(R.id.container, new UserFragment(), getFragmentManager());
        } else if (id == R.id.post_menu) {
            MainActivity.myPosts = false;
            changeFragment(R.id.container, new PostFragment(), getFragmentManager());
        } else if (id == R.id.my_posts_menu) {
            MainActivity.myPosts = true;
            changeFragment(R.id.container, new PostFragment(), getFragmentManager());

        } else if (id == R.id.profile_menu) {
            changeFragment(R.id.container, new ProfileFragment(), getFragmentManager());
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public static void changeFragment(int container, android.app.Fragment fragment, FragmentManager fragmentManager) {
        //FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(container, fragment);
        //transaction.add();
        transaction.commit();
    }
    public static void setValuesUserDrawer(Context context){
        User user= OperationUser.singUser();
        drawerEmail.setText(user.email);
        drawerName.setText(user.name);
        OperationUser.loadImage(context,drawerAvatar,user.image);
    }

}
