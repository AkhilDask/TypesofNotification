package com.example.notificationtypes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.media.session.MediaSessionCompat;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private NotificationManagerCompat notificationmanager;
    private EditText editTitle;
    private EditText editmsg;
    private MediaSessionCompat mediasession;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        notificationmanager = NotificationManagerCompat.from(this);
        editTitle = findViewById(R.id.editTextTextPersonName);
        editmsg = findViewById(R.id.editTextTextPersonName2);

        mediasession = new MediaSessionCompat(this,"tag");
    }
    public void sendonchannel1(View v) {
        String title=editTitle.getText().toString();
        String message=editmsg.getText().toString();
        Intent activityintent = new Intent(this,MainActivity.class);
        PendingIntent contentintent= PendingIntent.getActivity(this,0,activityintent,0);
        Intent broadcast = new Intent(this,NotificationReceiver.class);
        broadcast.putExtra("toastmessage","this is from channel1");
        PendingIntent actionintent= PendingIntent.getBroadcast(this,0,broadcast,PendingIntent.FLAG_UPDATE_CURRENT);

        Bitmap largeicon = BitmapFactory.decodeResource(getResources(),R.drawable.bell);

        Notification notification= new NotificationCompat.Builder(this,App.Channel_1_id)
                .setSmallIcon(R.drawable.ic_one) //essential one
                .setContentTitle(title)
                .setContentText(message)
                .setLargeIcon(largeicon)//optional
                .setStyle(new NotificationCompat.BigPictureStyle()
                        .bigPicture(largeicon)
                .bigLargeIcon(null))
                //.setStyle(new NotificationCompat.BigTextStyle()
                //.bigText(getString(R.string.longtext))
                //.setBigContentTitle("THE WEB SERIES")
                //.setSummaryText("this just fun"))
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                //.setColor(Color.BLUE) //to set color
                //.setContentIntent(contentintent) // we dont use intent filter becuase the call it explicitly
                .setAutoCancel(true) //to dismiss the notification when user touches it
                //.addAction(R.mipmap.ic_launcher,"Toast",actionintent) //to show the toast
                .build();
        notificationmanager.notify(1,notification);

    }
    public void sendonchannel2(View v) {
        String title=editTitle.getText().toString();
        String message=editmsg.getText().toString();

        Bitmap art = BitmapFactory.decodeResource(getResources(),R.drawable.music);

        Notification notification= new NotificationCompat.Builder(this,App.Channel_2_id)
                .setSmallIcon(R.drawable.ic_two)
                .setContentTitle(title)
                .setContentText(message)
                .setLargeIcon(art)
                .addAction(R.drawable.ic_pause,"Pause",null) //index0
                .addAction(R.drawable.ic_play,"Play",null)   //index1
                .addAction(R.drawable.ic_replay,"Replay",null) //index2
                .setStyle(new androidx.media.app.NotificationCompat.MediaStyle()
                .setShowActionsInCompactView(0,1,2)  //order of actions in view
                .setMediaSession(mediasession.getSessionToken())) //used for controlling media player controls
                .setSubText("The music")

                //.setStyle(new NotificationCompat.InboxStyle() //can add upto 7 lines
                //.addLine("this is avenger")
                //.addLine("money heist")
                //.addLine("the witcher")
                //.addLine("can you say it")
                //.addLine("help the poor")
                //.setBigContentTitle("THEY ARE BEST")
                //.setSummaryText("nice to watch"))
                .setPriority(NotificationCompat.PRIORITY_LOW)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .build();
        notificationmanager.notify(2,notification);


    }
}