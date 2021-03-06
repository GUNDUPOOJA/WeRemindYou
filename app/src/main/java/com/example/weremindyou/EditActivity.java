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
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Random;

public class EditActivity extends AppCompatActivity {

    DBHelper helper = new DBHelper(this);
    List<Alarm> arrayList;
    boolean edit_action = false;
    int selected_id =0;
    String action = "no";
    Intent i;
    TextView tv_address;
    CheckBox enabled_checkbox;
    RadioGroup radioGroup;
    RadioButton selectedPriority,low,medium,high;
    EditText editText_Name;
    boolean isEnabled ;
    String name,selectedRbText,selectedDay,selectedMonth,selectedAddress,lat,lng,selected_lat,selected_lng;
    Calendar calendar;
    Button dateButton;
    Button timeButton;
    Button btn_done;
    Button btn_location;
    TimePickerDialog timePicker;
    DatePickerDialog datePicker;
    int mYear, mMonth, mDay, mHour, mMinute,enable=1,action_alarm_id;
    int selectedDate,selectedHour,selectedMinute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        init();
        gettingValuesFromViews();

    }

    // In this method priority of the remainder i.e high,medium,low is set and also
    //used toast to display a message if nothing selected.
    // In this the date and time buttons are also created
    private void gettingValuesFromViews() {


        timeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // time picker dialog
                timePicker = new TimePickerDialog(EditActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int sHour, int sMinute) {
                            selectedHour = sHour;
                            selectedMinute = sMinute;

                            calendar.set(Calendar.MINUTE, selectedMinute);
                            calendar.set(Calendar.HOUR_OF_DAY, selectedHour);
                        Toast.makeText(EditActivity.this, sHour + ":" + sMinute,Toast.LENGTH_SHORT).show();
                    }
                },mHour,mMinute,false);
                timePicker.show();
            }
        });
        dateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get Current Date
                datePicker = new DatePickerDialog(EditActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int year, int monthOfYear
                                    , int dayOfMonth) {
                                String[] monthName = {"JAN","FEB","MAR","APR","MAY","JUN","JUL","AUG","SEP","OCT","NOV","DEC"};
                                String[] daysName = new String[] { "Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat" };

                                Calendar calendar = Calendar.getInstance();
                                calendar.set(year,monthOfYear,dayOfMonth); //Important line
                                selectedDate = dayOfMonth;
                                selectedDay = daysName[calendar.get(Calendar.DAY_OF_WEEK) - 1];
                                selectedMonth = monthName[calendar.get(Calendar.MONTH)];
                                Log.d("Date",selectedDate+"");
                                //  Log.d("Month",(calendar.get(Calendar.MONTH) + 1)+""); // month in number

                                Log.d("Month",selectedMonth+""); // OK

                                Log.d("Current day = ",selectedDay+"");
                                Log.d("Year","-------------------------------------");



//                                Toast.makeText(EditActivity.this, dayOfMonth + "-"
//                                        + (monthOfYear + 1) + "-" + year, Toast.LENGTH_SHORT).show();

                            }
                        },mYear,mMonth,mDay);// Current date we are setting to DatePicker
                datePicker.show();

            }
        });

        btn_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(EditActivity.this,MapsActivity.class);
                intent.putExtra("action",edit_action+"");
                intent.putExtra("lat",selected_lat);
                intent.putExtra("lng",selected_lng);
                startActivityForResult(intent, 2);// Activity is started with requestCode 2
            }
        });

        btn_done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name = editText_Name.getText().toString();
                isEnabled = ((CheckBox) findViewById(R.id.enabled)).isChecked();

                int selectedRadioButtonId = radioGroup.getCheckedRadioButtonId();
                if (selectedRadioButtonId != -1) {
                    selectedPriority = findViewById(selectedRadioButtonId);
                    selectedRbText = selectedPriority.getText().toString();
//                    Toast.makeText(EditActivity.this, "Title: "+name+"\nEnabled: "+isEnabled +"\nPriority: "+selectedRbText, Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(EditActivity.this, "Nothing selected from the radio group", Toast.LENGTH_SHORT).show();
                }
                if(isEnabled){
                    enable = 1;
                }
                else enable = 0;

                try {
                    scheduleAlarm(edit_action);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    // Call Back method  to get the Message form other Activity
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        // check if the request code is same as what is passed  here it is 2
        if(requestCode==2)
        {
            String message=data.getStringExtra("Address");
            String lat_map = Double.toString(data.getDoubleExtra("lat",0)) ;
            String lng_map = Double.toString(data.getDoubleExtra("lng",0));
            selectedAddress = message;
            lat = lat_map ;
            lng = lng_map;

            tv_address.setText(selectedAddress);


        }
    }

    private void init() {

        final Calendar cldr = Calendar.getInstance();
        mHour = cldr.get(Calendar.HOUR_OF_DAY);
        mMinute = cldr.get(Calendar.MINUTE);

        selectedHour = mHour;
        selectedMinute = mMinute;

        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);

        String[] monthName = {"JAN","FEB","MAR","APR","MAY","JUN","JUL","AUG","SEP","OCT","NOV","DEC"};
        String[] daysName = new String[] { "Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat" };

        selectedDate = mDay;
        selectedDay = daysName[c.get(Calendar.DAY_OF_WEEK) - 1];
        selectedMonth = monthName[c.get(Calendar.MONTH)];

        i = new Intent(getApplicationContext(),MainActivity.class);

        editText_Name = findViewById(R.id.name);
        tv_address = findViewById(R.id.placeadd);
        radioGroup = findViewById(R.id.radiobutton_priority);
        low = findViewById(R.id.low);
        medium = findViewById(R.id.medium);
        medium.setChecked(true);
        high = findViewById(R.id.high);
        enabled_checkbox = findViewById(R.id.enabled);
        btn_done = findViewById(R.id.done);
        dateButton = findViewById(R.id.date_button);
        btn_location = findViewById(R.id.getadd);
        timeButton = findViewById(R.id.time_button);
        calendar = Calendar.getInstance();

        if(getIntent().getStringExtra("action")!=null){
            action = getIntent().getStringExtra("action");

            Log.d("alarm",action_alarm_id+" ");

            if(action.contains("edit")){
                edit_action = true;
                Log.d("action","edit");
                action_alarm_id = getIntent().getIntExtra("alarm_id",0);
                Log.d("action",action_alarm_id+" ");
                selected_id = action_alarm_id;
                arrayList = helper.getAlarmbyID(action_alarm_id);

                //Alarm alarm = new Alarm(alarm_id,title,enabled,priority,hour,minute,date,month,day,location);
                editText_Name.setText(arrayList.get(0).getTitle());
                enabled_checkbox.setChecked(arrayList.get(0).isEnabled()==1);
                String p = arrayList.get(0).getPriority();
                Log.d("priority 2",p+" ");
                if(p.contains("Low")) low.setChecked(true);
                if(p.contains("Medium")) medium.setChecked(true);
                if(p.contains("High")) high.setChecked(true);

                selectedDate = arrayList.get(0).getDate();
                selectedDay = arrayList.get(0).getDay();
                selectedMonth = arrayList.get(0).getMonth();

                selectedHour = arrayList.get(0).getHour();
                selectedMinute = arrayList.get(0).getMinute();
                tv_address.setText(arrayList.get(0).getLocation());
                selectedAddress = arrayList.get(0).getLocation();
                selected_lat = arrayList.get(0).getLat();
                selected_lng = arrayList.get(0).getLng();
                Log.d("priority 2",p+" ");


            }
        }
    }

    private void scheduleAlarm(boolean edit_action)  {
        int alarmId = new Random().nextInt(1000);
        Log.d("alarmId",selected_id+"");
        Alarm alarm = new Alarm(selected_id,alarmId,name,enable,selectedRbText,selectedHour,selectedMinute,selectedDate,selectedMonth,selectedDay,selectedAddress,lat,lng);
        Log.d("edit before schedule",edit_action+"");

        alarm.schedule(getApplicationContext(),alarm,calendar,edit_action,false);

        Intent i =new Intent(this,MainActivity.class);
        startActivity(i);
    }

    public void onCancelClick(View view)
    {
        startActivity(i);
    }


}
