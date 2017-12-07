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
 * Interface for entering a date for an event being edited
 */
public class EventDatePicker extends DialogFragment implements DatePickerDialog.OnDateSetListener
{

    private long result = 12;

    /**
     * Show the dialog for picking a date
     * @param savedInstanceState the set of data stored in the background
     * @return The dialog for picking a date
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
     * Called when the OK button is clicked in the date picker
     * @param view view of the datepicker that was set
     * @param year the year of the date picked
     * @param month the month of the date picked
     * @param day the day of the date picked
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
     * Return the date that was chosen
     * @return the date picker's result
     */
    public Long getDate()
    {
        return result;
    }



}
