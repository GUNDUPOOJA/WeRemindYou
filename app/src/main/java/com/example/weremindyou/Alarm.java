package com.example.weremindyou;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import java.util.Calendar;

import static android.Manifest.permission_group.LOCATION;
import static com.example.weremindyou.AlarmBroadcastReceiver.TITLE;

public class Alarm {

    DBHelper helper;
    private int id;
    private int alarmId;
    private int hour, minute;
    private int enabled,date;
    private String title,priority,day,month,location, lat, lng;

    public Alarm(int id,int alarmId, String title , int enabled, String priority, int hour, int minute, int date,  String month, String day, String location, String lat, String lng) {
        this.id = id;
        this.alarmId = alarmId;
        this.hour = hour;
        this.minute = minute;
        this.month = month;
        this.day = day;
        this.date = date;
        this.enabled = enabled;
        this.title = title;
        this.priority = priority;
        this.location = location;
        this.lat = lat;
        this.lng = lng;
    }

    public Alarm() {

    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public int getAlarmId() {
        return alarmId;
    }

    public void setAlarmId(int alarmId) {
        this.alarmId = alarmId;
    }
    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
    public String getDay() {
        return day;
    }
    public void setDay(String day) {
        this.day = day;
    }

    public int isEnabled() {
        return enabled;
    }

    public void setEnabled(int enabled) {
        this.enabled = enabled;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public Alarm(int alarmId, int hour, int minute) {
        this.alarmId = alarmId;
        this.hour = hour;
        this.minute = minute;
    }

    public void schedule(Context context, Alarm alarm,Calendar calendar,boolean edit_action,boolean snooze) {
        Log.d("edit inside schedule",edit_action+"");
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

        Intent intent = new Intent(context, AlarmBroadcastReceiver.class);
        intent.putExtra(TITLE, alarm.getTitle());
        intent.putExtra(LOCATION, alarm.getLocation());

        PendingIntent alarmPendingIntent = PendingIntent.getBroadcast(context, alarmId, intent, 0);
//        Calendar calendar = Calendar.getInstance();
//        calendar.setTimeInMillis(System.currentTimeMillis());
//        calendar.set(Calendar.HOUR_OF_DAY, hour);
//        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        // if alarm time has already passed, increment day by 1
        if (calendar.getTimeInMillis() <= System.currentTimeMillis()) {
            calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH) + 1);
        }
        if(!snooze) {
            String toastText = null;
            try {
                toastText = String.format("One Time Alarm %s scheduled for %s at %02d:%02d ", title, alarm.getDay(), alarm.getHour(), alarm.getMinute());

            } catch (Exception e) {
                e.printStackTrace();
            }
            Toast.makeText(context, toastText, Toast.LENGTH_LONG).show();

            helper = new DBHelper(context);
            if (edit_action) {
                Log.d("edit inside if", edit_action + "");
                helper.updateAlarm(alarm);
            } else {
                helper.addAlarm(alarm);
            }
        }else {
            Toast.makeText(context, "Alarm snoozed for 10 minutes", Toast.LENGTH_SHORT).show();
        }
        alarmManager.setExact(
                AlarmManager.RTC_WAKEUP,
                calendar.getTimeInMillis(),
                alarmPendingIntent
        );
    }
}
