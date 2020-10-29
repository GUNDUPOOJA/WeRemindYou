package com.example.weremindyou;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class Ringing extends AppCompatActivity {
    private final String TAG = "Ringing";

    private class PlayTimerTask extends TimerTask
    {
        @Override
        public void run()
        {
            Log.d(TAG, "PalyTimerTask.run()");
            //addNotification(task);
            finish();
        }
    }

    private Ringtone ringtone;
    private long playTime;
    private Timer timer = null;
    private PlayTimerTask playTimerTask;

    Task task;
    private TextView textView;
    @Override
    protected void onCreate(Bundle bundle)
    {
        Log.i(TAG, "onCreate");

        super.onCreate(bundle);

        getWindow().addFlags(
                WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON |
                        WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED |
                        WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON);

        setContentView(R.layout.ringing);

        textView = (TextView)findViewById(R.id.name);

        playTime = (long)30000;
        ringtone = RingtoneManager.getRingtone(getApplicationContext(), Settings.System.DEFAULT_RINGTONE_URI);

        start(getIntent());
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();

        stop();
    }

    private void start(Intent intent)
    {
        Log.d(TAG, "Start ringing...");

        task = new Task();
        task.fromIntent(intent);

        textView.setText(task.getName());

        playTimerTask = new PlayTimerTask();
        timer = new Timer();
        timer.schedule(playTimerTask, playTime);
        ringtone.play();
    }

    private void stop()
    {
        Log.d(TAG, "Stop ringing...");
        timer.cancel();
        ringtone.stop();
    }


}