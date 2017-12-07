package cs_3354.calendar_dbsf;

import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.widget.TimePicker;

import java.util.Calendar;

/**
 * Created by Trent on 12/5/2017.
 */

public class EventTimePicker extends DialogFragment implements TimePickerDialog.OnTimeSetListener
{

    private int hour;
    private int minute;

    /**
     *
     * @param savedInstanceState saves the Instance state of the activity
     * @return dialog
     */
    @Override
    public TimePickerDialog onCreateDialog(Bundle savedInstanceState)
    {
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);

        return new TimePickerDialog(getActivity(), this, hour, minute,
                DateFormat.is24HourFormat(getActivity()));
    }

    /**
     * Sets the hour and the minute for the event
     * @param view view associated with this function
     * @param h hour on time set
     * @param m minute on time set
     */
    public void onTimeSet(TimePicker view, int h, int m)
    {
        hour = h;
        minute = m;
    }

    /**
     *
     * @return hour
     */
    public int getHour()
    {
        return hour;
    }
    
    /**
     *
     * @return minute
     */
    public int getMinute()
    {
        return minute;
    }
}
