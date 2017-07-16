package com.example.kpn.custom_notifications;

import android.app.NotificationManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    int NOTIFICATION_ID=1;

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
                    nm.notify(NOTIFICATION_ID,builder.build());
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                builder.setProgress(0,0,false);
                builder.setContentText("Download Complete");
                nm.notify(NOTIFICATION_ID,builder.build());
            }
        }).start();
    }

}
