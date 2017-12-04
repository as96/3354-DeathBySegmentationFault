package cs_3354.calendar_dbsf;

import android.app.DatePickerDialog;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.Date;

public class CreateEventActivity extends AppCompatActivity
{
    String name;
    String type;
    Date startDate;
    Date endDate;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        startDate = new Date();
        endDate = new Date();

        initDatePickers();
    }

    /**
     * Initializes the date pickers for the start and end dates
     * Should be called by onCreate()
     */
    private void initDatePickers()
    {
        Calendar currentDate = Calendar.getInstance();
        final int currentYear = currentDate.get(Calendar.YEAR);
        final int currentMonth = currentDate.get(Calendar.MONTH);
        final int currentDay = currentDate.get(Calendar.DAY_OF_MONTH);

        //Start date picker
        final EditText startDateBox = (EditText)findViewById(R.id.text_startDate);
        startDateBox.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                DatePickerDialog datePicker = new DatePickerDialog(CreateEventActivity.this, new DatePickerDialog.OnDateSetListener()
                {
                    public void onDateSet(DatePicker datePicker, int selectedYear, int selectedMonth, int selectedDay)
                    {
                        startDate.setYear(selectedYear);
                        startDate.setMonth(selectedMonth);
                        startDate.setDate(selectedDay);

                        //Set the text box to reflect the chosen date
                        //Note: we must add 1 to the month because it is zero-indexed
                        startDateBox.setText((int)(selectedMonth + 1) + "/" + selectedDay + "/" + selectedYear);
                    }
                },currentYear, currentMonth, currentDay);
                datePicker.setTitle("Select start date");
                datePicker.show();  }
        });

        //End date picker
        final EditText endDateBox = (EditText)findViewById(R.id.text_endDate);
        endDateBox.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                DatePickerDialog datePicker = new DatePickerDialog(CreateEventActivity.this, new DatePickerDialog.OnDateSetListener()
                {
                    public void onDateSet(DatePicker datePicker, int selectedYear, int selectedMonth, int selectedDay)
                    {
                        endDate.setYear(selectedYear);
                        endDate.setMonth(selectedMonth);
                        endDate.setDate(selectedDay);

                        //Set the text box to reflect the chosen date
                        //Note: we must add 1 to the month because it is zero-indexed
                        endDateBox.setText((int)(selectedMonth + 1) + "/" + selectedDay + "/" + selectedYear);
                    }
                },currentYear, currentMonth, currentDay);
                datePicker.setTitle("Select end date");
                datePicker.show();  }
        });
    }

}
