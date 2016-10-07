package com.example.bangash.todolist;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TimePicker;

import java.util.Calendar;

public class addingActivity extends AppCompatActivity {
    EditText etTitle, etDiscription;
    ImageButton btnTime, btnDate;
    ImageButton btnCancel, btnSave;
    dataManipulation manipulation;

    String Titles, Description, AM_PM, mm_precede="";
    int Year, Month, Day, Hours, Minutes;
    private int Date_Dialog_ID = 0, Time_Dialog_ID = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adding);

        etTitle = (EditText) findViewById(R.id.etTitle);
        etDiscription = (EditText) findViewById(R.id.etDescription);
        btnTime = (ImageButton) findViewById(R.id.btnTime);
        btnDate = (ImageButton) findViewById(R.id.btnDate);
        btnCancel = (ImageButton) findViewById(R.id.btnCancel);
        btnSave = (ImageButton) findViewById(R.id.btnSave);
        manipulation = new dataManipulation(this);

////////////////////////////////////////////////////Calender setting for current values///////////////////////////////////////////////

        Calendar myCalender=Calendar.getInstance();
        Year=myCalender.get(Calendar.YEAR);
        Month=myCalender.get(Calendar.MONTH);
        Day=myCalender.get(Calendar.DAY_OF_MONTH);
        Hours=myCalender.get(Calendar.HOUR_OF_DAY);
        Minutes=myCalender.get(Calendar.MINUTE);

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

                if (etTitle.getText().length() <= 0) {
                    etTitle.setError("Title Can't Be Empty!");
                } else if (etDiscription.getText().length() <= 0) {
                    etDiscription.setError("Give Some Description!");
                } else {
                    SettingAM_PM();
                    addingData();
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


    ///////////////////////////////////////////Adding data/////////////////////////////////////////////////////

    public void addingData() {
        Titles = etTitle.getText().toString();
        Description = etDiscription.getText().toString();
        manipulation.InsertingData(addingActivity.this, Titles, Year, Month+1, Day, Hours, mm_precede+ Minutes , AM_PM, Description);
    }

    ///////////////////////////////////////////Setting am_pm/////////////////////////////////////////////////////
    public void SettingAM_PM() {
        AM_PM = " AM";
        if (Hours >= 12) {
            AM_PM = " PM";
            if (Hours >= 13 && Hours < 24) {
                Hours -= 12;
            } else {
                Hours = 12;
            }
        } else if (Hours == 0) {
            Hours = 12;
        }
        if (Minutes < 10) {
            mm_precede = "0";
        }

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
            Month = monthOfYear + 1;
            Day = dayOfMonth;
        }
    };
    TimePickerDialog.OnTimeSetListener timeListener = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            Hours = hourOfDay;
            Minutes = minute;
            SettingAM_PM();
        }
    };
}



