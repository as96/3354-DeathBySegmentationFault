package cs_3354.calendar_dbsf;

/**
 * Created by Alec on 12/2/2017.
 */

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public class GridAdapter extends ArrayAdapter
{
    private LayoutInflater mInflater;
    private List<Date> monthlyDates;
    private Calendar currentDate;
    private List<Event> allEvents;


    //Make a way to paramterize the color string? Maybe a makeColorString method?

    public GridAdapter(Context context, List<Date> monthlyDates, Calendar currentDate, List<Event> allEvents)
    {
        super(context, R.layout.single_cell_layout);
        this.monthlyDates = monthlyDates;
        this.currentDate = currentDate;
        this.allEvents = allEvents;
        mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        Date mDate = monthlyDates.get(position);
        Calendar dateCal = Calendar.getInstance();
        dateCal.setTime(mDate);
        int dayValue = dateCal.get(Calendar.DAY_OF_MONTH);
        int displayMonth = dateCal.get(Calendar.MONTH) + 1;
        int displayYear = dateCal.get(Calendar.YEAR);
        int currentMonth = currentDate.get(Calendar.MONTH) + 1;
        int currentYear = currentDate.get(Calendar.YEAR);
        View view = convertView;
        if(view == null)
        {
            view = mInflater.inflate(R.layout.single_cell_layout, parent, false);
        }
        if(displayMonth == currentMonth && displayYear == currentYear)
        {
            view.setBackgroundColor(Color.parseColor("#ffd9d0"));
        }
        else
        {
            view.setBackgroundColor(Color.parseColor("#cccccc"));
        }

        //Add days to calendar
        TextView cellNumber = (TextView)view.findViewById(R.id.calendar_date_id);
        cellNumber.setText(String.valueOf(dayValue));

        //Add events to the calendar
        TextView eventIndicator = (TextView)view.findViewById(R.id.event_id);
        Calendar eventCalendar = Calendar.getInstance();
        for(int i = 0; i < allEvents.size(); i++)
        {
            eventCalendar.setTime(allEvents.get(i).getStartDate());
            if(dayValue == eventCalendar.get(Calendar.DAY_OF_MONTH) && displayMonth == eventCalendar.get(Calendar.MONTH) + 1
                    && displayYear == eventCalendar.get(Calendar.YEAR))
            {
                eventIndicator.setBackgroundColor(Color.parseColor("#FF4081"));
            }
        }
        return view;
    }

    @Override
    public int getCount()
    {
        return monthlyDates.size();
    }

    @Nullable
    @Override
    public Object getItem(int position)
    {
        return monthlyDates.get(position);
    }

    @Override
    public int getPosition(Object item)
    {
        return monthlyDates.indexOf(item);
    }
}