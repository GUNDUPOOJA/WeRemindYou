package com.example.weremindyou;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;



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
//                Log.d(g,"testing the app");
               Intent editIntent = new Intent(MainActivity.this, EditActivity.class);
                startActivity(editIntent);
            }


        });
    }
}