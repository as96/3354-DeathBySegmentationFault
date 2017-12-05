package cs_3354.calendar_dbsf;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

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
        // Inflate the layout for this fragment
        //I'm supposed to instantiate the Custom Calendar View
        View v =  inflater.inflate(R.layout.fragment_monthly_view, container, false);

        //here be testing garbage
        GregorianCalendar cal = new GregorianCalendar();
        Bundle dateBundle = getArguments();
        long time = dateBundle.getLong("date");
        cal.setTimeInMillis(time);
        CustomCalendarView ccv = new CustomCalendarView(v.getContext());
        LinearLayout layout = new LinearLayout(getActivity());
        layout.addView(ccv);
        //

        return v;
    }
}
