package cs_3354.calendar_dbsf;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;

import static java.util.Locale.getDefault;

/**
 * Created by Trent on 11/30/2017.
 */

/**
 * Handles the creation of the daily view fragment layout
 */
public class DailyViewFragment extends Fragment {

    private LinearLayout layout;
    private DailyViewFragment fragment;

    /**
     * Creates the view hierarchy associated with this fragment
     * @param inflater The LayoutInflater object that can be used to inflate any views in the fragment
     * @param container If non-null, this is the parent view that the fragment's UI should be
     *                  attached to. The fragment should not add the view itself, but this can
     *                  be used to generate the LayoutParams of the view
     * @param savedInstanceState If non-null, this fragment is being re-constructed from a
     *                           previous saved state as given here.
     * @return The instantiated view for this fragment
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        fragment = this;
        final ViewGroup viewGroup = container;
        final View v = inflater.inflate(R.layout.content_daily_view, container, false);
        layout = v.findViewById(R.id.linear_layout);
        GregorianCalendar cal = new GregorianCalendar();
        Bundle dateBundle = getArguments();
        long time = dateBundle.getLong("date", 0);
        Date fragmentDay = new Date(time);
        fragmentDay.setHours(0);
        fragmentDay.setMinutes(0);
        fragmentDay.setSeconds(0);
        cal.setTimeInMillis(time);
        String title = "" + String.valueOf(cal.getDisplayName(Calendar.MONTH, Calendar.LONG, getDefault())) + " " +
                String.valueOf(cal.get(Calendar.DAY_OF_MONTH)) + ", " +
                String.valueOf(cal.get(Calendar.YEAR));

        Toolbar toolbar = v.findViewById(R.id.toolbar);
        toolbar.setTitle(title);

        Date earliestOfDay = new Date();
        earliestOfDay.setTime(fragmentDay.getTime());
        Date latestOfDay = new Date();
        latestOfDay.setTime(earliestOfDay.getTime() + (1000*60*60*24) - 1001);
        EventListManager eventManager = EventListManager.getInstance();
        int count = 0;
        for (int i = 0; i < eventManager.getAllEvents().length; i++)
        {
            Event e = eventManager.getAllEvents()[i];
            if (e.getStartDate().getTime() > earliestOfDay.getTime() &&
                e.getEndDate().getTime() < latestOfDay.getTime())
                addEventButton(e);
            count++;
        }


        Toast.makeText(getActivity(), "Found " + String.valueOf(count) + " events",
                Toast.LENGTH_SHORT).show();

        return v;
    }

    /**
     * Adds an event on the appropriate daily view. This is a button because we can press it and
     * take it to another activity.
     * @param ev the event to be added as a button
     */
    public void addEventButton(Event ev)
    {
        final Event e = ev;
        final long startTime = e.getStartDate().getTime();
        final Button event = new Button(getActivity());
        event.setTextColor(Color.BLACK);
        int startHour = e.getStartDate().getHours();
        String hour = startHour > 12 ? "PM" : "AM";
        startHour = startHour > 12 ? (startHour - 12) : startHour;
        Date sDate = e.getStartDate();
        String startMinute;
        if (sDate.getMinutes() < 10)
            startMinute = "0" + sDate.getMinutes();
        else
            startMinute = String.valueOf(sDate.getMinutes());
        event.setText(String.valueOf(startHour) + ":" + startMinute + " " +
                hour + " " + e.getName());
        layout.addView(event);
        event.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                DeleteEvent dialog = DeleteEvent.newInstance(startTime);
                dialog.setParams(layout, event, fragment, e);
                dialog.show(getActivity().getFragmentManager(), "dialog");
            }
        });
    }

}
