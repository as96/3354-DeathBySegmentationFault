package cs_3354.calendar_dbsf;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by Trent on 12/2/2017.
 */

public class DismissNotification extends BroadcastReceiver
{

    @Override
    public void onReceive(Context context, Intent intent)
    {
        int id = intent.getIntExtra("notificationId", 1);
        NotificationManager notifManager = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
        notifManager.cancel(id);
    }

}