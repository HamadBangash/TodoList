package com.example.bangash.todolist;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.NotificationBuilderWithBuilderAccessor;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Bangash on 10/8/2016.
 */
public class notificationReceiver extends BroadcastReceiver {
    static int id = 0;

    @Override
    public void onReceive(Context context, Intent intent) {

        dataManipulation manipulation = new dataManipulation(context);
        String title = intent.getStringExtra("Title");
        PendingIntent pi;
        NotificationManager notif_manager = (NotificationManager) context.getSystemService(context.NOTIFICATION_SERVICE);
        Intent intent1 = new Intent(context.getApplicationContext(), detailsActivity.class);
        intent.putExtra("Title", title);
        pi = PendingIntent.getActivity(context, 0, intent, 0);
        Notification notification = new Notification.Builder(context)
                .setContentTitle(title)
                .setContentText("")
                .setAutoCancel(true)
                .setSmallIcon(R.drawable.bell)
                .setContentIntent(pi).build();
        notif_manager.notify(id, notification);
        id = id + 1;

        Toast.makeText(context, "OK", Toast.LENGTH_SHORT).show();
    }
}
