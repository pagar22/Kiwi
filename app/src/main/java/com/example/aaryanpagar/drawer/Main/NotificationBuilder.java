package com.example.aaryanpagar.drawer.Main;

import android.annotation.SuppressLint;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.util.Log;

import com.example.aaryanpagar.drawer.Assignments.OnNotificationClickAss;
import com.example.aaryanpagar.drawer.R;
import com.example.aaryanpagar.drawer.Reminders.OnNotificationClickRem;
import com.example.aaryanpagar.drawer.Tests.OnNotificantionClickTest;

public class NotificationBuilder extends BroadcastReceiver {
    String title;
    String reminderTask;
    String date;
    String whonot;
    Intent intent1;
    int notId;
    static int NOTIFICATION_ID = 0;
    @SuppressLint("ResourceAsColor")
    @Override
    public void onReceive(Context context, Intent intent) {
        reminderTask= intent.getStringExtra("reminder");
        whonot= intent.getStringExtra("who's notify");
        Uri SoundUri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);

        if(whonot.equals("rem")) {
            title= "Reminder";
            notId= intent.getIntExtra("remId",1);
            intent1 = new Intent(context, OnNotificationClickRem.class);
            intent1.setFlags(Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
            intent1.putExtra("reminderName", reminderTask);
        }
        else
            if(whonot.equals("tests")) {
                title= "Test";
                notId= intent.getIntExtra("testId",1);
                intent1 = new Intent(context, OnNotificantionClickTest.class);
                intent1.setFlags(Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
                date= intent.getStringExtra("dateTest");
                intent1.putExtra("datepass", date);
                intent1.putExtra("subject", reminderTask);
                intent1.putExtra("bench",(intent.getStringExtra("bench")));
                intent1.putExtra("topic",(intent.getStringExtra("topic")));
            }
            else
                if(whonot.equals("ass")){
                    title= "Assignment";
                    notId= intent.getIntExtra("assId", 1);
                    intent1 = new Intent(context, OnNotificationClickAss.class);
                    intent1.setFlags(Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
                    date= intent.getStringExtra("dateAss");
                    intent1.putExtra("subject2", (intent.getStringExtra("subject")));
                    intent1.putExtra("assi", reminderTask);
                    intent1.putExtra("datepass2", date);
                }
        PendingIntent pendingIntent= PendingIntent.getActivity(context, notId,
                intent1, PendingIntent.FLAG_CANCEL_CURRENT);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            String CHANNEL_ID = "test";
            CharSequence name = "test";
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel mChannel = new NotificationChannel(CHANNEL_ID, name, importance);
        }
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "test")
                .setSmallIcon(R.drawable.kiwi_icon)
                .setColor(R.color.colorPrimary)
                .setContentTitle(title)
                .setContentText(reminderTask)
                .setAutoCancel(true)
                .setSound(SoundUri)
                .setContentIntent(pendingIntent)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);
        notificationManager.notify(notId, builder.build());
    }
}
