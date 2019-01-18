package com.example.guilherme.notifyme;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {


    private static final String PRIMARY_CHANNEL_ID = "primary_notification_channel";
    private static final String UPDATE_NOTIFICATION = BuildConfig.APPLICATION_ID + ".UPDATE_NOTIFICATION";
    private static final int NOTIFICATION_ID = 0;
    private NotificationReceiver mReceiver = new NotificationReceiver();
    private Button mNotifyBtn;
    private Button mCancelBtn;
    private Button mUpdateBtn;

    private NotificationManager mManager;

    public void deleteAction(View view) {
            mManager.deleteNotificationChannel(PRIMARY_CHANNEL_ID);
    }


    public class NotificationReceiver extends BroadcastReceiver{
        public NotificationReceiver(){

        }

        @Override
        public void onReceive(Context context, Intent intent) {
            //update the notification
            updateNotification();

        }
    }

    private NotificationCompat.Builder getNotificationBuilder(){

        Intent intent = new Intent(this,MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,NOTIFICATION_ID,intent,PendingIntent.FLAG_UPDATE_CURRENT);




        NotificationCompat.Builder notifyBuilder = new NotificationCompat.Builder(
                this,
                PRIMARY_CHANNEL_ID

        );

        notifyBuilder.setContentTitle("You've been notified!")
                .setContentText("This is your notification text.")
                .setSmallIcon(R.drawable.ic_stat_money)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setDefaults(NotificationCompat.DEFAULT_ALL);
        return notifyBuilder;
    }


    public void createNotificationChannel(){
        mManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            // create a NotificationChannel
            NotificationChannel channel = new NotificationChannel(
                    PRIMARY_CHANNEL_ID,
                    "CICCC Notification",
                    NotificationManager.IMPORTANCE_HIGH
            );
        channel.enableLights(true);
        channel.setLightColor(Color.RED);
        channel.enableVibration(true);
        channel.setDescription("Notification from CICCC");

        mManager.createNotificationChannel(channel);
        }
    }

    private void setNotificationButtonState(boolean notify, boolean update, boolean cancel){
        mNotifyBtn.setEnabled(notify);
        mUpdateBtn.setEnabled(update);
        mCancelBtn.setEnabled(cancel);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        registerReceiver(mReceiver,new IntentFilter(UPDATE_NOTIFICATION));

        mNotifyBtn = findViewById(R.id.notifybtn);
        mCancelBtn = findViewById(R.id.cancelbtn);
        mUpdateBtn = findViewById(R.id.updatebtn);

        mNotifyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //send notification
                sendNotification();
            }
        });

        mUpdateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateNotification();

            }
        });

        mCancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelNotification();
            }
        });


        createNotificationChannel();

        setNotificationButtonState(true,false,false);
    }

    @Override
    protected void onDestroy() {
        unregisterReceiver(mReceiver);
        super.onDestroy();
    }

    public void cancelNotification(){
        mManager.cancel(NOTIFICATION_ID);
        setNotificationButtonState(true,false,false);
    }

    public void sendNotification(){

            Intent updateIntent = new Intent(UPDATE_NOTIFICATION);
            PendingIntent updatePendingIntent = PendingIntent.getBroadcast(this,NOTIFICATION_ID,updateIntent,PendingIntent.FLAG_ONE_SHOT);

            NotificationCompat.Builder notifyBuilder = getNotificationBuilder();
            notifyBuilder.addAction(R.drawable.ic_update,"Update Notification",updatePendingIntent);

            mManager.notify(NOTIFICATION_ID,notifyBuilder.build());
            setNotificationButtonState(false,true,true);
    }

    public void updateNotification(){
            Bitmap androidImage = BitmapFactory.decodeResource(getResources(),R.drawable.mascot_1);
            NotificationCompat.Builder  builder = getNotificationBuilder();
            builder.setStyle(
                    new NotificationCompat
                            .BigPictureStyle()
                            .bigPicture(androidImage)
                            .setBigContentTitle("Notification Updated!")
            );

            mManager.notify(NOTIFICATION_ID,builder.build());
            setNotificationButtonState(false,false,true);
    }

}
