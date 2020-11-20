package com.example.weremindyou;


public class Alarm {

    DBHelper helper;
    private int id;
    private int alarmId;
    private int hour, minute;
    private int enabled,date;
    private String title,priority,day,month,location;

    public Alarm(int id,int alarmId, String title , int enabled, String priority, int hour, int minute, int date,  String month, String day, String location) {
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

}
