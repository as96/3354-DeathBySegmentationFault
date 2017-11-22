package cs_3354.calendar_dbsf;

import android.app.DialogFragment;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Icon;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;

import static android.content.Context.NOTIFICATION_SERVICE;
import static android.support.v4.app.NotificationManagerCompat.IMPORTANCE_HIGH;

/**
 * Created by Trent on 11/18/2017.
 *
 * This activity exists only because AlarmScreen (a DialogFragment) cannot be triggered by
 * AlarmManager, so it needs an Activity whose sole purpose is to create it, which
 * AlarmManager would then trigger.
 */

public class AlarmActivity extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent){
        final String NOTIFICATION_CHANNEL_ID = "4655";
        String channelName = "AlarmChannel";
        int importance = NotificationManager.IMPORTANCE_HIGH;
        int notificationID = 001;
        NotificationChannel notificationChannel = new NotificationChannel(NOTIFICATION_CHANNEL_ID, channelName, importance);
        /*notificationChannel.enableLights(true);
        notificationChannel.setLightColor(Color.RED);
        notificationChannel.enableVibration(true);
        notificationChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});*/

        Intent dismissIntent = new Intent(context, DismissNotification.class);
        Bundle notifIDBundle = new Bundle();
        notifIDBundle.putInt("notificationID", notificationID);
        dismissIntent.putExtras(notifIDBundle);
        PendingIntent pIntent = PendingIntent.getBroadcast(context, 2, dismissIntent, 0);

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.createNotificationChannel(notificationChannel);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, NOTIFICATION_CHANNEL_ID)
                //set status bar icon - credit goes to Freepik https://www.flaticon.com/authors/freepik
                .setSmallIcon(R.drawable.notification_icon)
                .setContentTitle("Event is happening")
                .setContentText("X Event is happening")
                .addAction(R.drawable.notification_icon, "Dismiss", pIntent)
                .setDefaults(Notification.DEFAULT_ALL)
                .setCategory(Notification.CATEGORY_REMINDER);
        builder.setPriority(NotificationCompat.PRIORITY_HIGH);
        notificationManager.notify(notificationID, builder.build());
    }

    /*
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        DialogFragment fragment = new AlarmScreen();
        fragment.show(getFragmentManager(), "alarm");
    }*/

}
