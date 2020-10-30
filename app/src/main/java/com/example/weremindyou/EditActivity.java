package com.example.weremindyou;


import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TimePicker;


import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class EditActivity extends AppCompatActivity {


    private Task task;
    private GregorianCalendar calendar;
    private int year;
    private int month;
    private int day;
    private int hour;
    private int minute;
    RadioGroup radioGroup;
    RadioButton selectedPriority;
    EditText editText_Name;
    boolean isEnabled ;
    String name;
    private Button dateButton;
    private Button timeButton;
    Button btn_done;

    static final int DATE_DIALOG_ID = 0;
    static final int TIME_DIALOG_ID = 1;

    private static SimpleDateFormat timeFormat = new SimpleDateFormat("H:mm");
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("E MMM d, yyyy");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);


        dateButton = (Button)findViewById(R.id.date_button);
        timeButton = (Button)findViewById(R.id.time_button);

        task = new Task();
        task.fromIntent(getIntent());
        calendar = new GregorianCalendar();
        calendar.setTimeInMillis(task.getDate());
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        hour = calendar.get(Calendar.HOUR_OF_DAY);
        minute = calendar.get(Calendar.MINUTE);

        updateButtons();
    }
    private void gettingValuesFromViews() {

        radioGroup.getCheckedRadioButtonId();
        btn_done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name = editText_Name.getText().toString();
                isEnabled = ((CheckBox) findViewById(R.id.enabled)).isChecked();
            }
        });
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

    @Override
    protected Dialog onCreateDialog(int id)
    {
        if (DATE_DIALOG_ID == id)
            return new DatePickerDialog(this, dateSetListener, year, month, day);
        else if (TIME_DIALOG_ID == id)
            return new TimePickerDialog(this, timeSetListener, hour, minute, true);
        else
            return null;
    }
    @Override
    protected void onPrepareDialog(int id, Dialog dialog)
    {
        if (DATE_DIALOG_ID == id)
            ((DatePickerDialog)dialog).updateDate(year, month, day);
        else if (TIME_DIALOG_ID == id)
            ((TimePickerDialog)dialog).updateTime(hour, minute);
    }

    public void onDateClick(View view){
        showDialog(DATE_DIALOG_ID);
    }

    public void onTimeClick(View view)
    {
        showDialog(TIME_DIALOG_ID);
    }


    private DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener()
    {
        public void onDateSet(DatePicker view, int year_, int month_, int day_)
        {
            year = year_;
            month = month_;
            day = day_;

            calendar = new GregorianCalendar(year, month, day, hour, minute);
            task.setDate(calendar.getTimeInMillis());

            updateButtons();
        }
    };

    private TimePickerDialog.OnTimeSetListener timeSetListener = new TimePickerDialog.OnTimeSetListener()
    {
        public void onTimeSet(TimePicker view, int hour_, int minute_)
        {
            hour = hour_;
            minute = minute_;

            calendar = new GregorianCalendar(year, month, day, hour, minute);
            task.setDate(calendar.getTimeInMillis());

            updateButtons();
        }
    };
    private void updateButtons()
    {
        dateButton.setText(formatDate(task));
//        Log.d(TAG, "updated date button");
        timeButton.setText(formatTime(task));
//        Log.d(TAG, "updated time button");
    }
    public static String formatTime(Task Task)
    {
        return timeFormat.format(new Date(Task.getDate()));
    }

    public static String formatDate(Task Task)
    {
        return dateFormat.format(new Date(Task.getDate()));
    }
}
