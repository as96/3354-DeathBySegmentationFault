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
 * TO-DO: Implement repeating alarms feature (try different set method in AlarmManager?)
 *        Allow AlarmScreen to find the event that is storing the corresponding Alarm.
 *
 * NOTE: In order to accomodate the method getSystemService(), "minSdkVersion" in build.gradle
 *       has been changed from 21 to 23.
 *       Also, when testing on an emulator, the alarm shows up when the emulator's time is the
 *       entered time, not the time on your computer (testing has shown these to not be the same.)
 *
 * To use, create an alarm and supply the year, month, day, hour, minute, second, and context
 * (usually "this") in that order. Remember that months are 0 indexed, meaning, for example, that
 * February is 1 and not 2. Hours are represented in military time.
 *
 * Example: Alarm a = new Alarm(2017, 10, 8, 22, 39, 0, this); creates an alarm
 * set for Nov. 8, 2017 at 10:39:00 in the emulated phone's time.
 */

public class Alarm
{
    private GregorianCalendar date;

    public Alarm(int y, int m, int d, int h, int min, int s, Context c)
    {

        date = new GregorianCalendar(y, m, d, h, min, s);

        AlarmManager aManager = (AlarmManager)c.getSystemService(AlarmManager.class);
        Intent i = new Intent(c, AlarmScreen.class);

        //This will start an AlarmManager that will bring up the app's alarm screen whenever
        //the time comes for the event that scheduled it.
        aManager.setExact(AlarmManager.RTC_WAKEUP, date.getTimeInMillis(),
                PendingIntent.getActivity(c, 1, i, 0));

        Log.i("ALARM SET", "An alarm was set for " + String.valueOf(date.getTime()) + ". ");

    }

}
