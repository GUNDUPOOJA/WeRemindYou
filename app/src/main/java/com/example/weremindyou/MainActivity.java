package com.example.weremindyou;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    private Task currentTask;
    private final int NEW_ACTIVITY = 0;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;
    private NavigationView navigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            Intent in = new Intent(this, EditActivity.class);
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            drawerLayout = (DrawerLayout) findViewById(R.id.activity_main);
            toggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);

            drawerLayout.addDrawerListener(toggle);
            toggle.syncState();

            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }catch(Exception e){
            Log.d(String.valueOf(e),"Exception");
        }

//        navigationView = (NavigationView)findViewById(R.id.nv);
//        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//
//                int id = item.getItemId();
//                Class fragmentClass = TasksFragment.class;
//                switch(id)
//                {
//                    case R.id.dashboard:
////                        Log.d(TAG, "Choosing tasks");
//                        break;
//                    case R.id.help:
//                        fragmentClass = HelpFragment.class;
////                        Log.d(TAG, "Choosing help");
//                        //Intent intent = new Intent(getBaseContext(), HelpActivity.class);
//                        //DashBoardActivity.this.startActivity(intent);
//                        break;
//                    case R.id.exit:
//                        finish();
//                        moveTaskToBack(true);
//                        break;
//                }
//                switchFragment(fragmentClass);
//
//                drawerLayout.closeDrawer(GravityCompat.START);
//                return true;
//            }
//        });
//
//        switchFragment(TasksFragment.class);
    }
    public void onAddClick(View view)
    {
        String tag="";
        try {
            Log.d(tag,"on Click");
            Intent intent = new Intent(getBaseContext(), EditActivity.class);

            currentTask = new Task();
            currentTask.toIntent(intent);

            MainActivity.this.startActivityForResult(intent, NEW_ACTIVITY);
        }catch(Exception e)
        {

        }
    }
//    void switchFragment(Class fragmentClass){
//        Fragment fragment = null;
//        try {
//            fragment = (Fragment) fragmentClass.newInstance();
//        }
//        catch (Exception e) {
//            Log.d(TAG, e.getMessage());
//        }
//        FragmentManager fragmentManager = getSupportFragmentManager();
//        fragmentManager.beginTransaction().replace(R.id.tasksfragment, fragment).commit();
//    }
}