package com.example.bangash.todolist;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
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
import java.util.Stack;

/**
 * Created by Bangash on 10/8/2016.
 */
public class notificationReceiver extends BroadcastReceiver {
    int id = 1;

    @Override
    public void onReceive(Context context, Intent intent) {
        String title = intent.getStringExtra("Title");
        String description = intent.getStringExtra("Des");
        int arrivedId = intent.getIntExtra("id", 0);
//        if (arrivedId == id) {


        PendingIntent pi;
        NotificationManager notif_manager = (NotificationManager) context.getSystemService(context.NOTIFICATION_SERVICE);
        Intent intent1 = new Intent(context.getApplicationContext(), detailsActivity.class);
        intent1.putExtra("Title", title);
        intent1.putExtra("Des", description);
//        TaskStackBuilder builder = TaskStackBuilder.create(context);
//        builder.addParentStack(detailsActivity.class);
//        builder.addNextIntent(intent1);
        pi = PendingIntent.getActivity(context, 0, intent1,PendingIntent.FLAG_UPDATE_CURRENT);
        Notification notification = new Notification.Builder(context)
                .setContentTitle(title)
                .setContentText(description)
                .setAutoCancel(true)
                .setSmallIcon(R.drawable.bell)
                .setContentIntent(pi).build();
        notif_manager.notify(0, notification);
        Toast.makeText(context, "OK", Toast.LENGTH_SHORT).show();
        id++;
//        } else {
//            Toast.makeText(context, "Problem Occurred!!", Toast.LENGTH_SHORT).show();
//        }


    }
}
