package com.example.bangash.todolist;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class addingActivity extends AppCompatActivity {
    EditText etTitle, etDiscription;
    ImageButton btnTime, btnDate;
    ImageButton btnCancel, btnSave;
    dataManipulation manipulation;

    String Titles, Description, AM_PM = "", mm_precede = "";
    int Year, Month, Day, Hours, Minutes;
    int Hours2, Minutes2;
    private int Date_Dialog_ID = 0, Time_Dialog_ID = 1;

    Calendar myCalender;
    Calendar myCalender2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adding);

        SettingAM_PM();

        etTitle = (EditText) findViewById(R.id.tvTitle);
        etDiscription = (EditText) findViewById(R.id.etDescription);
        btnTime = (ImageButton) findViewById(R.id.btnTime);
        btnDate = (ImageButton) findViewById(R.id.btnDate);
        btnCancel = (ImageButton) findViewById(R.id.btnCancel);
        btnSave = (ImageButton) findViewById(R.id.btnSave);
        manipulation = new dataManipulation(this);

////////////////////////////////////////////////////Calender setting for current values///////////////////////////////////////////////

        myCalender = Calendar.getInstance();
        myCalender2 = Calendar.getInstance();
        Year = myCalender.get(Calendar.YEAR);
        Month = myCalender.get(Calendar.MONTH);
        Day = myCalender.get(Calendar.DAY_OF_MONTH);
        Hours = myCalender.get(Calendar.HOUR_OF_DAY);
        Minutes = myCalender.get(Calendar.MINUTE);
        Hours2 = myCalender.get(Calendar.HOUR_OF_DAY);

////////////////////////////////////////////////////Button listeners///////////////////////////////////////////////////////////////

        btnDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(Date_Dialog_ID);

            }
        });
        btnTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(Time_Dialog_ID);


            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (myCalender2.get(Calendar.HOUR_OF_DAY) > Hours2 || myCalender2.get(Calendar.MINUTE) > Minutes2) {
                    Toast.makeText(addingActivity.this, "Invalid Time Set", Toast.LENGTH_SHORT).show();
                    Log.d("Minute current", myCalender2.get(Calendar.MINUTE) + "");
                    Log.d("Minute set", Minutes2 + "");

                } else {
                    SettingAM_PM();
                    addingData();
                    settingNotifications();
                    finish();
                }
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

///////////////////////////////////////////Setting notification/////////////////////////////////////////////////////

    public void settingNotifications() {
        int id = 1;
        myCalender.set(Calendar.HOUR_OF_DAY, Hours2);
        myCalender.set(Calendar.MINUTE, Minutes2);
        Intent intent = new Intent(addingActivity.this, notificationReceiver.class);
//        intent.putExtra("id",id);
        intent.putExtra("Title", etTitle.getText().toString());
        intent.putExtra("Des", etDiscription.getText().toString());
        PendingIntent pendingIntent = PendingIntent.getBroadcast(addingActivity.this, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        alarmManager.set(alarmManager.RTC_WAKEUP, myCalender.getTimeInMillis(), pendingIntent);
        id++;

    }

//////////////////////////////////////////////Adding data/////////////////////////////////////////////////////

    public void addingData() {

        if (Minutes < 10) {
            mm_precede = "0";
        }
        Titles = etTitle.getText().toString();
        Description = etDiscription.getText().toString();
        Log.d("Hours Add", Hours + "");
        Log.d("Am add", AM_PM);
        manipulation.InsertingData(addingActivity.this, Titles, Year, Month + 1, Day, Hours, mm_precede + Minutes, AM_PM, Description);
    }

    ///////////////////////////////////////////Setting am_pm/////////////////////////////////////////////////////
    public void SettingAM_PM() {

        if (Hours > 12) {
            Hours -= 12;
            AM_PM = "PM";
        } else if (Hours == 0) {
            Hours += 12;
            AM_PM = "AM";
        } else if (Hours == 12)
            AM_PM = "PM";
        else
            AM_PM = "AM";


    }


    ///////////////////////////////////////////Date and time listeners/////////////////////////////////////////////////////

    @Override
    protected Dialog onCreateDialog(int id) {
        if (id == Date_Dialog_ID) {
            return new DatePickerDialog(addingActivity.this, dateListener, Year, Month, Day);
        }
        if (id == Time_Dialog_ID) {
            return new TimePickerDialog(addingActivity.this, timeListener, Hours, Minutes, true);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener dateListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            Year = year;
            Month = monthOfYear;
            Day = dayOfMonth;
        }
    };
    TimePickerDialog.OnTimeSetListener timeListener = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            SettingAM_PM();
            Hours = hourOfDay;
            Minutes = minute;
            Hours2 = hourOfDay;
            Minutes2 = minute;


        }
    };
}



