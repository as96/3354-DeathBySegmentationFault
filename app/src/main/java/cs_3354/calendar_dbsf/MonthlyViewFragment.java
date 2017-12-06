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
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 *
 * Created by Alec and Hajung 11/30/17
 */
public class MonthlyViewFragment extends Fragment {

    public static HashMap<LayoutInflater, Long> inflaterDates;

    Context context;
    public MonthlyViewFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //fragmentDate.setTime(savedInstanceState.getLong("date"));
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_monthly_view, container, false);
        //This is where we set up the calendar

        Log.i("DATE,DUDE",inflater.toString());
        //Log.i("DATE,DUDE",String.valueOf(inflaterDates.get(inflater)));

        return v;
    }

    @Override
    public void onInflate(Activity activity, AttributeSet attrs, Bundle savedInstanceState)
    {
        super.onInflate(activity, attrs, savedInstanceState);
    }

    public void setTheme(View v)
    {
       Intent intent = new Intent(v.getContext(), themeActivity.class);
       startActivity(intent);

    }

}

