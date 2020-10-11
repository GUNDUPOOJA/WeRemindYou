package com.example.weremindyou;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {


    Button createTask;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createTask = findViewById(R.id.taskBtn);

        createTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent editIntent = new Intent(MainActivity.this, EditActivity.class);
                startActivity(editIntent);
                // setContentView(R.layout.activity_edit);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.nav_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Toast.makeText(this, "Selected Item: " +item.getTitle(), Toast.LENGTH_SHORT).show();
        switch (item.getItemId()) {
            case R.id.help:
                Intent helpint = new Intent(MainActivity.this, HelpFragment.class);
                startActivity(helpint);
                return true;
            case R.id.dashboard:
                Intent boardint = new Intent(MainActivity.this, MainActivity.class);
                startActivity(boardint);
                return true;
            case R.id.rateUs:
                Intent rateint= new Intent(MainActivity.this, rateUsActivity.class);
                startActivity(rateint);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }



    }

