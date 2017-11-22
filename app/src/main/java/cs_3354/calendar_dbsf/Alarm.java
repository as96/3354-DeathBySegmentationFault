package cs_3354.calendar_dbsf;
import java.util.GregorianCalendar;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

/**
 * Created by Trent on 11/6/2017.
 * This class sets up alarms for events.
 * TO-DO: Allow AlarmScreen to find the event that is storing the corresponding Alarm.
 *
 * NOTE: In order to accommodate the method getSystemService(), "minSdkVersion" in build.gradle
 *       has been changed from 21 to 23.
 *       Also, when testing on an emulator, the alarm shows up when the emulator's time is the
 *       entered time, not the time on your computer (testing has shown these to not be the same.)
 *
 * To use, create an alarm and supply a calendar object and context
 * (usually "this") in that order. If you need to do a repeating alarm, instead supply
 * a calendar, interval in milliseconds (cannot be less than 1 minute), and context.
 * Remember that months are 0 indexed, meaning, for example, that February is 1 and not 2.
 * Hours are represented in military time.
 *
 * Example: GregorianCalendar cal = new GregorianCalendar(2017, 10, 8, 22, 39, 0);
 *          Alarm a = new Alarm(cal, this);
 * creates an alarm set for Nov. 8, 2017 at 10:39:00 in the emulated phone's time.
 *          GregorianCalendar cal = new GregorianCalendar(2017, 10, 8, 22, 39, 0);
 *          Alarm a = new Alarm(cal, (1000*60*60*24), this);
 * creates an alarm set for Nov. 8, 2017 at 10:39:00 in the emulated phone's time that
 * repeats every day (1000 milliseconds * 60 * 60 * 24.)
 */

public class Alarm
{
    private GregorianCalendar date;
    private AlarmManager aManager;
    private Intent intent;
    private Context context;

    public Alarm(GregorianCalendar cal, Context c){

        date = cal;
        context = c;
        aManager = (AlarmManager)c.getSystemService(AlarmManager.class);
        intent = new Intent(c, AlarmActivity.class);

        //This will start an AlarmManager that will bring up a dialogue box whenever
        //the time comes for the event that scheduled it.
        aManager.setExact(AlarmManager.RTC_WAKEUP, date.getTimeInMillis(),
                PendingIntent.getBroadcast(c, 1, intent, 0));

        Log.i("ALARM SET", "An alarm was set for " + String.valueOf(date.getTime()) + ". ");

    }

    public Alarm(GregorianCalendar cal, long interval, Context c){

        date = cal;
        context = c;
        aManager = (AlarmManager)c.getSystemService(AlarmManager.class);
        intent = new Intent(c, AlarmActivity.class);

        //This will start an AlarmManager that will bring up a dialogue box whenever
        //the time comes for the event that scheduled it plus every interval
        aManager.setRepeating(AlarmManager.RTC_WAKEUP, date.getTimeInMillis(), interval,
                PendingIntent.getActivity(c, 1, intent, 0));

    }

    /*
     * Removes/cancels the alarm
     */
    public void deschedule(){
        aManager.cancel(PendingIntent.getActivity(context, 1, intent, 0));
    }

}
