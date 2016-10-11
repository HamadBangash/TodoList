package com.example.bangash.todolist;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.TextView;
import android.widget.Toast;

public class detailsActivity extends AppCompatActivity {
    TextView tvTitle, tvDescription;
    NotificationManager nm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        tvTitle = (TextView) findViewById(R.id.tvTitle);
        tvDescription = (TextView) findViewById(R.id.tvDescription);
        String title = getIntent().getStringExtra("Title");
        tvTitle.setText(title);
        String description = getIntent().getStringExtra("Des");
        tvDescription.setText(description);
        nm = (NotificationManager) this.getSystemService(NOTIFICATION_SERVICE);
        nm.cancel(0);


    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
          // this.onDestroy();
            this.finish();
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }
    @Override
    protected void onResume() {
        super.onResume();

    }
}
