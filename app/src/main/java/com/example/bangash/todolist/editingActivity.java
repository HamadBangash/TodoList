package com.example.bangash.todolist;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TimePicker;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class editingActivity extends AppCompatActivity {
    EditText etTitle, etDiscription;
    Button btnTime, btnDate;
    ImageButton btnCancel, btnUpdate;
    dataManipulation manipulation;

    String t_AM_PM, mm_precede = "";
    int t_Year, t_Month, t_Day, t_Hours, t_Minutes;

    List<String> Titles = new ArrayList<>();
    List<Integer> Year = new ArrayList<>();
    List<Integer> Month = new ArrayList<>();
    List<Integer> Day = new ArrayList<>();
    List<Integer> Hours = new ArrayList<>();
    List<String> Minutes = new ArrayList<>();
    List<String> AM_PM = new ArrayList<>();
    List<String> Description = new ArrayList<>();

    String titles, year, month, day, hours, minutes, am_pm, description;

    private int Date_Dialog_ID = 0, Time_Dialog_ID = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editing);

        etTitle = (EditText) findViewById(R.id.etTitle);
        etDiscription = (EditText) findViewById(R.id.etDescription);
        btnTime = (Button) findViewById(R.id.btnTime);
        btnDate = (Button) findViewById(R.id.btnDate);
        btnCancel = (ImageButton) findViewById(R.id.btnCancel);
        btnUpdate = (ImageButton) findViewById(R.id.btnUpdate);
        manipulation = new dataManipulation(this);

////////////////////////////////////////////////////Calender setting for current values///////////////////////////////////////////////

        Calendar myCalender = Calendar.getInstance();
        t_Year = myCalender.get(Calendar.YEAR);
        t_Month = myCalender.get(Calendar.MONTH);
        t_Day = myCalender.get(Calendar.DAY_OF_MONTH);
        t_Hours = myCalender.get(Calendar.HOUR_OF_DAY);
        t_Minutes = myCalender.get(Calendar.MINUTE);

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
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        SettingData();
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = getIntent().getIntExtra("ID", 0);
                titles = etTitle.getText().toString();
                description = etDiscription.getText().toString();
                SettingAM_PM();
                manipulation.UpdatingData(titles, t_Year, t_Month, t_Day, t_Hours, mm_precede + t_Minutes, t_AM_PM, description, id);
                finish();
            }
        });


    }


    public void SettingData() {

        int id = getIntent().getIntExtra("ID", 0);
        Titles = manipulation.RetrievingTitlesById(this, id);
        Year = manipulation.RetrievingYearById(this, id);
        Month = manipulation.RetrievingMonthById(this, id);
        Day = manipulation.RetrievingDayById(this, id);
        Hours = manipulation.RetrievingHoursById(this, id);
        Minutes = manipulation.RetrievingMinutesById(this, id);
        AM_PM = manipulation.RetrievingAM_PMById(this, id);
        Description = manipulation.RetrievingDescriptionById(this, id);

        titles = Titles.toString().replaceAll("(^\\[|\\]$)", "");
        year = Year.toString().replaceAll("(^\\[|\\]$)", "");
        month = Month.toString().replaceAll("(^\\[|\\]$)", "");
        day = Day.toString().replaceAll("(^\\[|\\]$)", "");
        hours = Hours.toString().replaceAll("(^\\[|\\]$)", "");
        minutes = Minutes.toString().replaceAll("(^\\[|\\]$)", "");
        am_pm = AM_PM.toString().replaceAll("(^\\[|\\]$)", "");
        description = Description.toString().replaceAll("(^\\[|\\]$)", "");

        SettingAM_PM();
        etTitle.setText(titles);
        btnTime.setText(hours + ":" + mm_precede + minutes + t_AM_PM);
        btnDate.setText(day + "-" + month + "-" + year);
        etDiscription.setText(description);
    }

    public void SettingAM_PM() {
        t_AM_PM = " AM";
        if (t_Hours >= 12) {
            t_AM_PM = " PM";
            if (t_Hours >= 13 && t_Hours < 24) {
                t_Hours -= 12;
            } else {
                t_Hours = 12;
            }
        } else if (t_Hours == 0) {
            t_Hours = 12;
        }
        if (t_Minutes < 10) {
            mm_precede = "0";
        }

    }

    ///////////////////////////////////////////Date and time listeners/////////////////////////////////////////////////////

    @Override
    protected Dialog onCreateDialog(int id) {
        if (id == Date_Dialog_ID) {
            return new DatePickerDialog(editingActivity.this, dateListener, t_Year, t_Month, t_Day);
        }
        if (id == Time_Dialog_ID) {
            return new TimePickerDialog(editingActivity.this, timeListener, t_Hours, t_Minutes, true);
        }
        return null;
    }


    private DatePickerDialog.OnDateSetListener dateListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            t_Year = year;
            t_Month = monthOfYear + 1;
            t_Day = dayOfMonth;
            btnDate.setText(t_Day + "-" + t_Month + "-" + t_Year);
        }
    };
    TimePickerDialog.OnTimeSetListener timeListener = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            t_Hours = hourOfDay;
            t_Minutes = minute;
            SettingAM_PM();
            btnTime.setText(t_Hours + ":" + mm_precede + t_Minutes + t_AM_PM);
        }
    };
}



