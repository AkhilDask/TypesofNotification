package com.example.notificationtypes;

import android.app.Application;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;


public class App extends Application {
    public static final String Channel_1_id= "channel1";
    public static final String Channel_2_id= "channel2";
    @Override
    public void onCreate() {
        super.onCreate();

        createNotificationChannel();
    }
    private void createNotificationChannel(){
        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.O){
            NotificationChannel channel1 = new NotificationChannel(
                    Channel_1_id,
                    "notification1",
                    NotificationManager.IMPORTANCE_HIGH
            );
            channel1.setDescription("this is notification1");

            NotificationChannel channel2 = new NotificationChannel(
                    Channel_2_id,
                    "notification 2",
                    NotificationManager.IMPORTANCE_LOW
            );
            channel2.setDescription("this is notification 2");

            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel1);
            manager.createNotificationChannel(channel2);
        }
    }
}
