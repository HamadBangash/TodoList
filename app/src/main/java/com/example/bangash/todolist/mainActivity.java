package com.example.bangash.todolist;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class mainActivity extends AppCompatActivity {
    private ImageButton btnAdd;
    ListView lvItems;
    dataManipulation manipulation;
    TextView tvInfo;

    List<Integer> ID;
    List<String> Titles = new ArrayList<>();
    List<Integer> Year = new ArrayList<>();
    List<Integer> Month = new ArrayList<>();
    List<Integer> Day = new ArrayList<>();
    List<Integer> Hours = new ArrayList<>();
    List<String> Minutes = new ArrayList<>();
    List<String> AM_PM = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lvItems = (ListView) findViewById(R.id.listView);
        manipulation = new dataManipulation(this);
        retrievingData();
    }

    public void retrievingData() {
        ID = manipulation.RetrievingIds(this);
        Titles = manipulation.RetrievingTitles(this);
        Year = manipulation.RetrievingYear(this);
        Month = manipulation.RetrievingMonth(this);
        Day = manipulation.RetrievingDay(this);
        Hours = manipulation.RetrievingHours(this);
        Minutes = manipulation.RetrievingMinutes(this);
        AM_PM = manipulation.RetrievingAM_PM(this);
        customAdapter adapter = new customAdapter(mainActivity.this, ID, Titles, Year, Month, Day, Hours, Minutes, AM_PM);
        lvItems.setAdapter(adapter);
    }


    public class customAdapter extends ArrayAdapter {
        List<Integer> ID;
        List<String> Titles;
        List<Integer> Year;
        List<Integer> Month;
        List<Integer> Day;
        List<Integer> Hours;
        List<String> Minutes;
        List<String> AM_PM;
        Context context;
        dataManipulation manipulation = new dataManipulation(getContext());

        public customAdapter(Context context, List<Integer> ID, List<String> Titles,
                             List<Integer> Year, List<Integer> Month,
                             List<Integer> Day, List<Integer> Hours,
                             List<String> Minutes,
                             List<String> AM_PM
        ) {
            super(context, R.layout.custom_layout_for_items, Titles);
            this.context = context;
            this.Titles = Titles;
            this.Year = Year;
            this.Month = Month;
            this.Day = Day;
            this.Hours = Hours;
            this.Minutes = Minutes;
            this.AM_PM = AM_PM;
            this.ID = ID;

        }


        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            View view = inflater.inflate(R.layout.custom_layout_for_items, null);
            final TextView tvListTitle = (TextView) view.findViewById(R.id.tvListTitle);
            final ImageButton btnEdit = (ImageButton) view.findViewById(R.id.btnEdit);
            ImageButton btnDelete = (ImageButton) view.findViewById(R.id.btnDelete);
            TextView tvTime = (TextView) view.findViewById(R.id.tvTime);
            TextView tvDate = (TextView) view.findViewById(R.id.tvDate);
            tvListTitle.setText(Titles.get(position).toString());
            tvDate.setText(Day.get(position) + "/" + Month.get(position) + "/" + Year.get(position));

            tvTime.setText(Hours.get(position) + ":" + Minutes.get(position) + AM_PM.get(position));
            btnEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int id=ID.get(position);
                Intent intent=new Intent(mainActivity.this,editingActivity.class);
                    intent.putExtra("ID",id);
                    startActivity(intent);
                }
            });
            btnDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int i = ID.get(position);
                    manipulation.DeletingRow(i);
                    retrievingData();

                }
            });
            return view;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        retrievingData();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.app_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.Add) {
            Intent addingIntent = new Intent(mainActivity.this, addingActivity.class);
            startActivity(addingIntent);
        }


        return super.onOptionsItemSelected(item);


    }
}
