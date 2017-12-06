package cs_3354.calendar_dbsf;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.widget.DatePicker;
import android.widget.TimePicker;

import java.sql.Time;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Trent on 12/5/2017.
 */

public class EventDatePicker extends DialogFragment implements DatePickerDialog.OnDateSetListener
{

    private Long result = new Long(12);

    /**
     *
     * <-->TODO: why is 12 in the result field</-->
     * @param savedInstanceState saves the Instance state of the activity
     * @return dialog
     */
    @Override
    public DatePickerDialog onCreateDialog(Bundle savedInstanceState)
    {
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        return new DatePickerDialog(getActivity(), this, year, month, day);
    }

    /**
     * Sets date according to the selected time
     * @param view view associated with this function
     * @param year set year
     * @param month set month
     * @param day set day
     */
    public void onDateSet(DatePicker view, int year, int month, int day)
    {
        Calendar date = Calendar.getInstance();
        date.set(Calendar.YEAR, year);
        date.set(Calendar.MONTH, month);
        date.set(Calendar.DAY_OF_MONTH, day);
        result = date.getTimeInMillis();
    }

    /**
     *
     * @return date
     */
    public Long getDate()
    {
        return result;
    }



}
