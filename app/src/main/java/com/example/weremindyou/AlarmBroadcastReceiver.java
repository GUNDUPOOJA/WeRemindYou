package com.example.weremindyou;


import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;
public class AlarmBroadcastReceiver extends BroadcastReceiver {

    public static final String TITLE = "TITLE";

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("Broadcast","Received");
        String toastText = String.format("Alarm Reboot");
        Toast.makeText(context, toastText, Toast.LENGTH_SHORT).show();
        startAlarmService(context,intent);
    }


}
