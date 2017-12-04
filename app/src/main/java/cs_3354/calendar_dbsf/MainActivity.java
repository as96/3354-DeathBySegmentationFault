package cs_3354.calendar_dbsf;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class MainActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        Date current = new Date();
        current.setTime(current.getTime());
        Log.i("hai....", current.toString());
        Date current2 = new Date();
        current2.setTime(current.getTime() + 1);
        EventListManager eventListManager = EventListManager.getInstance();
        Event e = new Event(current, current2, "Dentist's Appointment", "Appointment");
        eventListManager.addEvent(e);
        Event[] es = eventListManager.getEventsBetween(new Date(current.getTime() - 1), current2);
        for (int i = 0; i < 100; i++){
            eventListManager.addEvent(e);
        }

        setContentView(R.layout.activity_main);
        Intent intent = new Intent(this, DailyViewActivity.class);
        startActivity(intent);

    }
}
