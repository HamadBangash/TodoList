package com.example.bangash.todolist;

import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

/**
 * Created by HP on 6/12/2016.
 */
public class RingtoneService extends Service {

    MediaPlayer mediaPlayer;
    AlertDialog alertDialog;
    boolean isRunning;
    PendingIntent pi;
    String title;
    String Title;

    @Override
    public void onCreate() {
        super.onCreate();

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String status = intent.getExtras().getString("extra", "");
        String des = intent.getExtras().getString("Des");
         title = intent.getExtras().getString("Title");
        String medicne = intent.getExtras().getString("mdicne");


        assert status != null;
        switch (status) {
            case "alarm on":
                startId = 1;
                break;
            case "alarm off":
                startId = 0;
                break;
            default:
                startId = 0;
                break;
        }

        if (!isRunning && startId == 1) {
            mediaPlayer = MediaPlayer.create(this, R.raw.countdown);
            mediaPlayer.start();
            isRunning = true;
            startId = 0;


            if (medicne != null) {
                //Notification Manager
                NotificationManager notif_manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                Intent intent_medince = new Intent(this.getApplicationContext(), detailsActivity.class);
                //   intent_medince.putExtra("Title",title);
                pi = PendingIntent.getActivity(this, 0, intent_medince, 0);
                Notification notification = new Notification.Builder(this)
                        .setContentTitle(title)
                        .setContentText(medicne + " time")
                        .setAutoCancel(true)
                        .setSmallIcon(R.drawable.bell)
                        .setContentIntent(pi).build();
                notif_manager.notify(0, notification);
            } else {
                int id=0;
                NotificationManager notif_manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                Intent intent_namaz = new Intent(this.getApplicationContext(), detailsActivity.class);
                pi = PendingIntent.getActivity(this, 0, intent_namaz, 0);
                Notification notification = new Notification.Builder(this)
                        .setContentTitle(title)
                        .setContentText(des)
                        .setAutoCancel(true)
                        .setSmallIcon(R.drawable.bell)
                        .setContentIntent(pi).build();
                notif_manager.notify(id, notification);
                id++;
                Log.d("id not",id+"");
            }

        } else if (!isRunning && startId == 0) {

            isRunning = false;
            startId = 0;

        } else if (isRunning && startId == 1) {
            isRunning = false;
            startId = 0;


        } else if (isRunning && startId == 0) {
            mediaPlayer.stop();
            mediaPlayer.reset();
            isRunning = false;
            startId = 0;

        } else {

            isRunning = false;
            startId = 0;


        }


        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }




}
