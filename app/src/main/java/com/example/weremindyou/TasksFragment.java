package com.example.weremindyou;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

public class TasksFragment {

    private ListView taskList;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.riders_login);
        taskList = findViewById(R.id.task_)
        Intent I = getIntent();


        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String w = username.getText().toString();
                String h = pswrd.getText().toString();

                //tCost = tCost + parseDouble(w) * parseDouble(h) * rate;

                Intent I = new Intent(getApplicationContext(), MainActivity.class);

                setResult(successCode, I);
                finish();

            }
        });

        rgstrbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent I = new Intent(getApplicationContext(), MainActivity.class);

                setResult(errorCode, I);
                finish();;

            }
        });

    }
}

}

