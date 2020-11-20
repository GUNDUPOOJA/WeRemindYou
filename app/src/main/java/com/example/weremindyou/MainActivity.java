package com.example.weremindyou;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.GregorianCalendar;


public class MainActivity extends AppCompatActivity {

    DBHelper helper = new DBHelper(this);
    Button createTask;
    RecyclerView recyclerView;
    ArrayList<Alarm> arrayList = new ArrayList<>();
    MyAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String t = null;
        if(t==null){
            Log.d("str45","is empty");}

        createTask = findViewById(R.id.taskBtn);
        recyclerView = findViewById(R.id.task_list);
        loadData();

        createTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent editIntent = new Intent(MainActivity.this, EditActivity.class);
                startActivity(editIntent);

            }
        });

    }
    public void loadData() {


        arrayList = helper.getAllData();
        recyclerView.setLayoutManager(new GridLayoutManager(this, 1));
        adapter = new MyAdapter(this, arrayList);
        recyclerView.setAdapter(adapter);
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

