package cs_3354.calendar_dbsf;

import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import java.util.Date;

/**
 * Created by Trent on 12/2/2017.
 * Snoozes the alarm notification for 5 minutes.
 */

public class SnoozeNotification extends BroadcastReceiver
{

    /**
     * Receives the broadcast for snoozing
     * @param context The Context in which the receiver is running.
     * @param intent The Intent being received.
     */
    @Override
    public void onReceive(Context context, Intent intent)
    {
        AlarmManager aManager = context.getSystemService(AlarmManager.class);
        Intent i = new Intent(context, AlarmNotification.class);

        aManager.setExact(AlarmManager.RTC_WAKEUP,
                new Date().getTime() + (1000 * 60 * 5),
                PendingIntent.getBroadcast(context, 1, i, 0));

        //Unfortunately, it is not so simple to just send a broadcast to DismissNotification
        //so we must duplicate its code.
        int id = intent.getIntExtra("notificationID", 1);
        NotificationManager notifManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notifManager.cancel(id);

    }
}
