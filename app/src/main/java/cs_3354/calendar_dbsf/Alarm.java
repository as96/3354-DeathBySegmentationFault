package cs_3354.calendar_dbsf;
import java.util.GregorianCalendar;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by Trent on 11/6/2017.
 * This class sets up alarms for events.
 * TO-DO: Allow AlarmNotification to find the event that is storing the corresponding Alarm.
 *
 * IMPORTANT:
 *       In order to accommodate Notifications, "minSdkVersion", "targetSdkVersion", and
 *       "compileSdkVersion" in build.gradle have been changed to 26, 27 and 27 respectively.
 *       The emulator and android studio must also be updated to API 27!
 *       Also, when testing on an emulator, the alarm shows up when the emulator's time is the
 *       entered time, not the time on your computer (testing has shown these to not be the same.)
 *
 * To use, create an alarm and supply a calendar object, a context (usually "this"), and
 * optionally an interval (for repeating alarms) in that order. Remember that months are
 * 0 indexed, meaning, for example, that February is 1 and not 2.
 * Hours are represented in military time.
 *
 * Example: GregorianCalendar cal = new GregorianCalendar(2017, 10, 8, 22, 39, 0);
 *          Alarm a = new Alarm(cal, this);
 * creates an alarm set for Nov. 8, 2017 at 10:39:00 in the emulated phone's time.
 *          GregorianCalendar cal = new GregorianCalendar(2017, 10, 8, 22, 39, 0);
 *          Alarm a = new Alarm(cal, (1000*60*60*24), this);
 * creates an alarm set for Nov. 8, 2017 at 10:39:00 in the emulated phone's time that
 * repeats every day (1000 milliseconds * 60 * 60 * 24.)
 *
 * Note that the repeating alarm is not 100% accurate. After the initial alarm, it may go
 * off up to 30 seconds (or possibly more, but no more than a minute) after it should
 * on subsequent triggers.
 */

public class Alarm
{

    AlarmManager aManager;
    PendingIntent pIntent;
    long interval;
    long time;
    Context context;
    final static long ALARM_NOT_REPEATING = 0;

    public Alarm(GregorianCalendar cal, Context c){
        setAlarm(cal.getTimeInMillis(), ALARM_NOT_REPEATING, c);
    }

    public Alarm(GregorianCalendar cal, long i, Context c){
        setAlarm(cal.getTimeInMillis(), i, c);
    }


    /*
     *Sets an alarm.
     *
     * @param t The time (or first time if repeating) in milliseconds to trigger at.
     * @param i The interval in milliseconds (0 if alarm is not repeating) between triggers.
     * @param c The Context this alarm is made in.
     */
    public void setAlarm(long t, long i, Context c){

        //Setting fields
        aManager = c.getSystemService(AlarmManager.class);
        interval = i;
        context = c;
        time = t;
        Intent intent = new Intent(c, AlarmNotification.class);
        pIntent = PendingIntent.getBroadcast(c, 1, intent, 0);

        //Setting the alarm
        if (i == ALARM_NOT_REPEATING)
            aManager.setExact(AlarmManager.RTC_WAKEUP, time, pIntent);
        else
            aManager.setRepeating(AlarmManager.RTC_WAKEUP, time, i, pIntent);

        //Logging the alarm
        String intText = (i == ALARM_NOT_REPEATING) ? "with intervals of " + String.valueOf(i)
                + " milliseconds." : ". ";
        Log.i("ALARM SET", "An alarm was set for " +
                String.valueOf(time) + intText);

    }

    /*
     * Removes/cancels the alarm
     */
    public void deschedule(){
        aManager.cancel(pIntent);
    }

    /*
     * Changes the interval
     *
     * @param i The new interval.
     */
    public void setInterval(long i){
        deschedule();
        long currentTime = android.os.SystemClock.currentThreadTimeMillis();
        while (currentTime > time)
            time = time + i;
        setAlarm(time, i, context);
    }

}