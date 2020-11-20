package com.example.weremindyou;


import android.content.Context;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import android.util.Log;
public class DBHelper extends SQLiteOpenHelper {

    SQLiteDatabase db;
    private static final String DATABASE_NAME = "Alarm1";
    private static final String TABLE_NAME = "Alarms1";


    String CREATE_TABLE = "Create Table IF NOT EXISTS " + TABLE_NAME + " ( " +
            "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
            " alaram_id INTEGER," +
            " title TEXT," +
            " enabled INTEGER," +
            " priority TEXT," +
            " hour INTEGER," +
            " minute INTEGER," +
            " date INTEGER," +
            " month String ," +
            " day TEXT," +
            " location TEXT)";
    public DBHelper(Context applicationcontext) {
        super(applicationcontext, DATABASE_NAME, null,1);
    }



    public void insertStudent(String s)
    {
        db = this.getWritableDatabase();
        db.execSQL(s);
    }

    public void toggle_enable(int id,int enable_status)
    {
        db = this.getWritableDatabase();
        enable_status = 1 - enable_status;
        String query = "UPDATE "+TABLE_NAME +" SET enabled = "+enable_status+" WHERE alaram_id = "+id;
        db.execSQL(query);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE);
        Log.d("Alarm","Alarm table created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}