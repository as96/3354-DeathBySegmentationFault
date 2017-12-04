package cs_3354.calendar_dbsf;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static java.util.Locale.getDefault;

/**
 * Created by Trent on 11/30/2017.
 */

public class DailyViewFragment extends Fragment {

    Context c;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.content_daily_view, container, false);
        LinearLayout layout = (LinearLayout) v.findViewById(R.id.linear_layout);
        GregorianCalendar cal = new GregorianCalendar();
        Bundle dateBundle = getArguments();
        long time = dateBundle.getLong("date", 0);
        cal.setTimeInMillis(time);
        String title = "" + String.valueOf(cal.getDisplayName(Calendar.MONTH, Calendar.LONG, getDefault())) + " " +
                String.valueOf(cal.get(Calendar.DAY_OF_MONTH)) + ", " +
                String.valueOf(cal.get(Calendar.YEAR));

        Toolbar toolbar = (Toolbar)v.findViewById(R.id.toolbar);
        toolbar.setTitle(title);

        Date earliestOfDay = new Date();
        earliestOfDay.setTime(time);
        earliestOfDay.setHours(0);
        earliestOfDay.setMinutes(0);
        earliestOfDay.setSeconds(0);
        Date latestOfDay = new Date();
        latestOfDay.setTime(earliestOfDay.getTime() + (1000*60*60*24) - 1001);
        EventListManager eventManager = EventListManager.getInstance();
        Event[] events = eventManager.getEventsBetween(earliestOfDay, latestOfDay);
        for (int i = 0; i < events.length; i++)
        {
            Button event = new Button(getActivity());
            event.setTextColor(Color.BLACK);
            int startHour = events[i].getStartDate().getHours();
            String hour = startHour > 12 ? "PM" : "AM";
            startHour = startHour > 12 ? (startHour - 12) : startHour;
            event.setText(String.valueOf(startHour) + ":" +
                            events[i].getStartDate().getMinutes() + " " +
                            hour + " " + events[i].getName());
            layout.addView(event);
        }


        return v;
    }
}
