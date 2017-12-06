package cs_3354.calendar_dbsf;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Layout;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.DateFormatSymbols;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 *
 * Created by Alec and Hajung 11/30/17
 */
public class MonthlyViewFragment extends Fragment
{

    public MonthlyViewFragment()
    {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //fragmentDate.setTime(savedInstanceState.getLong("date"));
        // Inflate the layout for this fragment
        //I'm supposed to instantiate the Custom Calendar View
        View v =  inflater.inflate(R.layout.fragment_monthly_view, container, false);

        //Update calendar based on the provided date and time
        GregorianCalendar cal = new GregorianCalendar();
        Bundle dateBundle = getArguments();
        long time = dateBundle.getLong("date");
        cal.setTimeInMillis(time);

        //Set month label to the correct month
        TextView monthLabel = (TextView)v.findViewById(R.id.text_Month);
        int month = cal.getTime().getMonth();
        String monthString = new DateFormatSymbols().getMonths()[month];
        monthLabel.setText(monthString);

        //Set year label to correct year
        TextView yearLabel = (TextView)v.findViewById(R.id.text_Year);
        int year = cal.getTime().getYear() + 1900;
        yearLabel.setText("" + year);

        return v;
    }
}

