package cs_3354.calendar_dbsf;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by Trent on 12/2/2017.
 * Allows the alarm notification to be dismissed
 */
public class DismissNotification extends BroadcastReceiver
{

    /**
     * Receives a broadcast intent
     * @param context The Context in which the receiver is running.
     * @param intent The Intent being received.
     */
    @Override
    public void onReceive(Context context, Intent intent)
    {
        int id = intent.getIntExtra("notificationId", 1);
        NotificationManager notifManager = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
        notifManager.cancel(id);
    }

}