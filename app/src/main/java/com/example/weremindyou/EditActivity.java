package com.example.weremindyou;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;


import androidx.appcompat.app.AppCompatActivity;

public class EditActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

    }
    public void onCancelClick(View view)
    {
        setResult(RESULT_CANCELED, null);
        finish();
    }
    public void onDoneClick(View v)
    {
        Intent in = new Intent(this,MainActivity.class);
//        setResult(RESULT_OK,in);
        startActivity(in);
      //  setContentView(R.layout.fragment_tasks);
//        finish();
    }
}
