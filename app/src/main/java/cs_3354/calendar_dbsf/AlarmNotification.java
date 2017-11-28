package cs_3354.calendar_dbsf;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;

import java.util.Date;

/**
 * Created by Trent on 11/18/2017.
 *
 * Creates the notification corresponding to an alarm.
 */

public class AlarmNotification extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        //Setting up the channel
        final String NOTIFICATION_CHANNEL_ID = "4655";
        String channelName = "AlarmChannel";
        int importance = NotificationManager.IMPORTANCE_HIGH;
        int notificationID = 001;
        NotificationChannel notificationChannel = new NotificationChannel(NOTIFICATION_CHANNEL_ID, channelName, importance);

        //Setting up the action for the dismiss button
        Intent dismissIntent = new Intent(context, DismissNotification.class);
        Bundle notifIDBundle = new Bundle();
        notifIDBundle.putInt("notificationID", notificationID);
        dismissIntent.putExtras(notifIDBundle);
        PendingIntent dpIntent = PendingIntent.getBroadcast(context, 2, dismissIntent, 0);

        //Setting up the action for the snooze button
        Intent snoozeIntent = new Intent(context, SnoozeNotification.class);
        snoozeIntent.putExtras(notifIDBundle);
        PendingIntent spIntent = PendingIntent.getBroadcast(context, (int) System.currentTimeMillis(), snoozeIntent, 0);

        //Building the notification manager
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.createNotificationChannel(notificationChannel);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, NOTIFICATION_CHANNEL_ID)
                //Credit for icon goes to Freepik https://www.flaticon.com/authors/freepik
                .setSmallIcon(R.drawable.alarm_icon)
                .setContentTitle("Event is happening")
                .setContentText("X Event is happening")
                .addAction(R.drawable.alarm_icon, "Dismiss", dpIntent)
                .addAction(R.drawable.alarm_icon, "Snooze", spIntent)
                .setDefaults(Notification.DEFAULT_ALL)
                .setCategory(Notification.CATEGORY_REMINDER);
        notificationManager.notify(notificationID, builder.build());
    }

}

class DismissNotification extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent){
        int id = intent.getIntExtra("notificationId", 1);
        NotificationManager notifManager = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
        notifManager.cancel(id);
    }

}

class SnoozeNotification extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent){
        AlarmManager aManager = context.getSystemService(AlarmManager.class);
        Intent i = new Intent(context, AlarmNotification.class);

        aManager.setExact(AlarmManager.RTC_WAKEUP,
                new Date().getTime() + (1000 * 60 * 5),
                 PendingIntent.getBroadcast(context, 1, i, 0));

        //Unfortunately, it is not so simple to just send a broadcast to DismissNotification
        //so we must duplicate its code.
        int id = intent.getIntExtra("notificationID", 1);
        NotificationManager notifManager = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
        notifManager.cancel(id);

    }

}