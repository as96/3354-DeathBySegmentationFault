package cs_3354.calendar_dbsf;

import android.app.Activity;
import android.os.Bundle;
/**
 * Created by Trent on 11/6/2017.
 * Barebones screen that will show when an alarm is triggered.
 * TO-DO: show name of event that the reminder is for.
 *        make the screen pretty.
 */

public class AlarmScreen extends Activity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alarm_screen);

    }

    @Override
    protected void onRestart(){super.onRestart();}

    @Override
    protected void onResume(){super.onResume();}

    @Override
    protected void onPause(){super.onPause();}

    @Override
    protected void onStop(){super.onStop();}

    @Override
    protected void onDestroy(){super.onDestroy();}
}
