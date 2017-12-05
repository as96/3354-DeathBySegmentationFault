package cs_3354.calendar_dbsf;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Date;

public class CreateEventActivity extends AppCompatActivity
{
    Calendar currentDate = Calendar.getInstance();
    final int currentYear = currentDate.get(Calendar.YEAR);
    final int currentMonth = currentDate.get(Calendar.MONTH);
    final int currentDay = currentDate.get(Calendar.DAY_OF_MONTH);
    final int currentHour = currentDate.get(Calendar.HOUR);
    final int currentMinute = currentDate.get(Calendar.MINUTE);

    String name;
    String type;
    Date startDate;
    Date endDate;

    boolean startDateSet = false;
    boolean startTimeSet = false;
    boolean endDateSet = false;
    boolean endTimeSet = false;

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
        initTimePickers();
    }

    /**
     * Validates all input
     * If input is valid, creates a new event and closes the activity
     * @param v the current view (passed automatically)
     */
    public void createEvent(View v)
    {
        EditText nameBox = (EditText)findViewById(R.id.text_name);
        name = nameBox.getText().toString();
        if(name.length() < 1)
        {
            Toast.makeText(CreateEventActivity.this, "Please name the event",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        EditText typeBox = (EditText)findViewById(R.id.text_type);
        type = typeBox.getText().toString();

        if(!startDateSet)
        {
            Toast.makeText(CreateEventActivity.this, "Please choose a start date",
                    Toast.LENGTH_SHORT).show();
            return;
        }
        if(!startTimeSet)
        {
            Toast.makeText(CreateEventActivity.this, "Please choose a start time",
                    Toast.LENGTH_SHORT).show();
            return;
        }
        if(!endDateSet)
        {
            Toast.makeText(CreateEventActivity.this, "Please choose an end date",
                    Toast.LENGTH_SHORT).show();
            return;
        }
        if(!endTimeSet)
        {
            Toast.makeText(CreateEventActivity.this, "Please choose an end time",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        //TODO check for time conflicts

        EventListManager elm = EventListManager.getInstance();
        elm.addEvent(new Event(startDate, endDate, name, type));
        finish();
    }

    /**
     * Initializes the date pickers for the start and end dates
     * Should be called by onCreate()
     */
    private void initDatePickers()
    {
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

                        startDateSet = true;
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

                        endDateSet = true;
                    }
                },currentYear, currentMonth, currentDay);
                datePicker.setTitle("Select end date");
                datePicker.show();
            }
        });
    }

    /**
     * Initializes time pickers for start time and end time
     * Should be called by onCreate()
     */
    private void initTimePickers()
    {
        //Start time picker
        final EditText startTimeBox = (EditText)findViewById(R.id.text_startTime);
        startTimeBox.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                TimePickerDialog timePicker;
                timePicker = new TimePickerDialog(CreateEventActivity.this, new TimePickerDialog.OnTimeSetListener()
                {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute)
                    {
                        startDate.setHours(selectedHour);
                        startDate.setMinutes(selectedMinute);

                        startTimeBox.setText( selectedHour + ":" + selectedMinute);

                        startTimeSet = true;
                    }
                }, currentHour, currentMinute, true);//24 hour time
                timePicker.setTitle("Select start time");
                timePicker.show();
            }
        });

        //End time picker
        final EditText endTimeBox = (EditText)findViewById(R.id.text_endTime);
        endTimeBox.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                TimePickerDialog timePicker;
                timePicker = new TimePickerDialog(CreateEventActivity.this, new TimePickerDialog.OnTimeSetListener()
                {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute)
                    {
                        endDate.setHours(selectedHour);
                        endDate.setMinutes(selectedMinute);

                        endTimeBox.setText( selectedHour + ":" + selectedMinute);

                        endTimeSet = true;
                    }
                }, currentHour, currentMinute, true);//24 hour time
                timePicker.setTitle("Select end time");
                timePicker.show();
            }
        });
    }
}
