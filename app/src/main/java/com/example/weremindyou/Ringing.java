package com.example.weremindyou;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class Ringing extends AppCompatActivity {

    Button btn_dismiss,btn_snooze;
    ImageView img_clock;
    String title,location;
    TextView tv_title,tv_location;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ringing);

        init();
        try {
            gettingIntent();
        }
        catch (Exception e){
            Toast.makeText(this, "Ringing Snoozed Alarm", Toast.LENGTH_SHORT).show();
        }

        clickListener();
        animateClock();

    }

    private void gettingIntent() {
        title =  getIntent().getStringExtra("title");
        if(title==null) tv_title.setText("Snoozed Reminder");
        else
            tv_title.setText(title+"");
        location =  getIntent().getStringExtra("location");
        if(location==null) tv_location.setText("");
        else
            tv_location.setText(location+"");

    }

    private void init() {
        btn_dismiss = findViewById(R.id.activity_ring_dismiss);
        btn_snooze = findViewById(R.id.activity_ring_snooze);
        tv_title = findViewById(R.id.textView_title);
        tv_location = findViewById(R.id.textView_location);

        img_clock = findViewById(R.id.activity_ring_clock);
    }
    private void clickListener() {
        btn_dismiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentService = new Intent(getApplicationContext(), AlarmService.class);
                getApplicationContext().stopService(intentService);
                finish();
            }
        });

        btn_snooze.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Calendar calendar = Calendar.getInstance();
                calendar.setTimeInMillis(System.currentTimeMillis());
                calendar.add(Calendar.MINUTE, 1);

                Alarm alarm = new Alarm();

                alarm.schedule(getApplicationContext(),alarm,calendar,true,true);

                Intent intentService = new Intent(getApplicationContext(), AlarmService.class);
                getApplicationContext().stopService(intentService);
                finish();
            }
        });
    }

    private void animateClock() {
        ObjectAnimator rotateAnimation = ObjectAnimator.ofFloat(img_clock, "rotation"
                , 0f, 20f, 0f, -20f, 0f);
        rotateAnimation.setRepeatCount(ValueAnimator.INFINITE);
        rotateAnimation.setDuration(800);
        rotateAnimation.start();
    }
}