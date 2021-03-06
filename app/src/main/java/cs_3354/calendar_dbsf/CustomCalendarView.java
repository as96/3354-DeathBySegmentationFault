package cs_3354.calendar_dbsf;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.LinearLayout;
import java.text.SimpleDateFormat;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.zip.Inflater;

/**
 * Created by Alec on 12/2/2017.
 */

public class CustomCalendarView extends LinearLayout
{
    private GridView calendarGridView;
    private static final int MAX_CALENDAR_COLUMN = 42;
    private Calendar cal = Calendar.getInstance(Locale.ENGLISH);
    private Context context;
    private GridAdapter mAdapter;

    public CustomCalendarView(Context context)
    {
        super(context);
    }

    public CustomCalendarView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        this.context = context;
        initializeUILayout();
        setUpCalendarAdapter();
        setGridCellClickEvents();

    }

    public CustomCalendarView(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
    }

    private void initializeUILayout()
    {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.calendar_layout, this);
        calendarGridView = view.findViewById(R.id.calendar_grid);
    }

    private void setGridCellClickEvents(){
        calendarGridView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                long l = mAdapter.getDate(position);
                Intent intent = new Intent(context, DailyViewActivity.class);
                intent.putExtra(DailyViewActivity.sDate, l);
                context.startActivity(intent);
            }
        });
    }

    private void setUpCalendarAdapter(){

        List<Date> dayValueInCells = new ArrayList<>();
        List<Event> eventList = Arrays.asList(EventListManager.getInstance().getAllEvents());
        Calendar mCal = (Calendar)cal.clone();
        mCal.set(Calendar.DAY_OF_MONTH, 1);
        int firstDayOfTheMonth = mCal.get(Calendar.DAY_OF_WEEK) - 1;
        mCal.add(Calendar.DAY_OF_MONTH, -firstDayOfTheMonth);
        while(dayValueInCells.size() < MAX_CALENDAR_COLUMN){
            dayValueInCells.add(mCal.getTime());
            mCal.add(Calendar.DAY_OF_MONTH, 1);
        }
        mAdapter = new GridAdapter(context, dayValueInCells, cal, eventList);
        calendarGridView.setAdapter(mAdapter);
    }
}

