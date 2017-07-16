package com.example.kpn.custom_notifications;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.view.View;
import android.widget.RemoteViews;

public class MainActivity extends AppCompatActivity {

    String TEXT="You have a update for Saarang Events. "
            +"Events timing changed from 4 pm to 6 pm";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }


    public void notify_progress(View view)
    {
        final NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        final NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        builder.setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("www.CinemaVilla.net")
                .setContentText("Download in progress.....");


        //TO CREATE A LONG TASK FOR PROGRESS BAR
        new Thread(new Runnable() {
            @Override
            public void run() {

                for(int i=0;i<=100;i+=5)
                {
                    builder.setProgress(100,i,false);
                    nm.notify(1,builder.build());
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                builder.setProgress(0,0,false);
                builder.setContentText("Download Complete");
                nm.notify(1,builder.build());
            }
        }).start();
    }

    public void custom_notification(View view)
    {
        Intent i = new Intent(this,result.class);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pi = PendingIntent.getActivity(this,123,i,PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder= new NotificationCompat.Builder(this);
        NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        //ALLOW US TO CREATE CUSTOM LAYOUT
        RemoteViews remoteViews = new RemoteViews(getPackageName(),R.layout.custom_notification);
        remoteViews.setTextViewText(R.id.title,"NEW MESSAGE!!!");
        remoteViews.setImageViewResource(R.id.image,R.drawable.saarang2);
        remoteViews.setTextViewText(R.id.text,TEXT);


        builder.setSmallIcon(R.mipmap.ic_launcher).setAutoCancel(true);
        builder.setCustomBigContentView(remoteViews);
        builder.setContentIntent(pi);

        nm.notify(2,builder.build());

    }

}
