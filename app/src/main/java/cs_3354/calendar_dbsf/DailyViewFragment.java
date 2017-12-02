package cs_3354.calendar_dbsf;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v4.widget.NestedScrollView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
        v.setPadding(0, 100, 0, 0);
        TextView titleDate = (TextView)v.findViewById(R.id.dateTitle);
        GregorianCalendar cal = new GregorianCalendar();
        Bundle dateBundle = getArguments();
        long time = dateBundle.getLong("date", 0);
        cal.setTimeInMillis(time);
        String title = "" + String.valueOf(cal.getDisplayName(Calendar.MONTH, Calendar.LONG, getDefault())) + " " +
                String.valueOf(cal.get(Calendar.DAY_OF_MONTH)) + ", " +
                String.valueOf(cal.get(Calendar.YEAR));
        titleDate.setText(title);

        LinearLayout layout = new LinearLayout(getActivity());
        TextView event1 = new TextView(DailyViewActivity.context);
        layout.addView(event1);


        return v;
    }
}
