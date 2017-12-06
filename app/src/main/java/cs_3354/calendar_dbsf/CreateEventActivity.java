package cs_3354.calendar_dbsf;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.icu.util.Calendar;
import android.icu.util.GregorianCalendar;
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
    Calendar currentDate = new GregorianCalendar();

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
        setTitle("Create Event");

        Bundle bundle = getIntent().getExtras();
        if (bundle != null)
        {
            currentDate.setTimeInMillis(bundle.getLong("date"));
        }

        startDate = new Date(currentDate.get(Calendar.YEAR), currentDate.get(Calendar.MONTH), currentDate.get(Calendar.DAY_OF_MONTH), currentDate.get(Calendar.HOUR), currentDate.get(Calendar.MINUTE));
        endDate = new Date(currentDate.get(Calendar.YEAR), currentDate.get(Calendar.MONTH), currentDate.get(Calendar.DAY_OF_MONTH), currentDate.get(Calendar.HOUR), currentDate.get(Calendar.MINUTE));

        initDatePickers();
        initTimePickers();
        initToCurrentTime();
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
            Toast.makeText(CreateEventActivity.this, "Please input an event name",
                    Toast.LENGTH_SHORT).show();
            return;
        }
        if(startDate.after(endDate))
        {
            Toast.makeText(CreateEventActivity.this, "Start date must be before end date",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        EditText typeBox = (EditText)findViewById(R.id.text_type);
        type = typeBox.getText().toString();

        final Event newEvent = new Event(startDate, endDate, name, type, getApplicationContext());
        final EventListManager elm = EventListManager.getInstance();

        //If there is a time conflict, display a dialog asking if this is okay
        if(elm.checkTimeConflicts(newEvent))
        {
            AlertDialog alertDialog = new AlertDialog.Builder(CreateEventActivity.this).create();
            alertDialog.setTitle("Time Conflict");
            alertDialog.setMessage("This event overlaps another event. Is this okay?");
            alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Yes",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                            elm.addEvent(newEvent);
                            finish();
                        }
                    });
            alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "No",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                            return;
                        }
                    });
            alertDialog.show();
        }
        else
        {
            elm.addEvent(newEvent);
            finish();
        }

        //TODO check for time conflicts

        elm.addEvent(new Event(startDate, endDate, name, type, this));
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
                    }
                },currentDate.get(Calendar.YEAR), currentDate.get(Calendar.MONTH), currentDate.get(Calendar.DAY_OF_MONTH));
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
                },currentDate.get(Calendar.YEAR), currentDate.get(Calendar.MONTH), currentDate.get(Calendar.DAY_OF_MONTH));
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
                    }
                }, currentDate.get(Calendar.HOUR), currentDate.get(Calendar.MINUTE), true);//24 hour time
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
                    }
                }, currentDate.get(Calendar.HOUR), currentDate.get(Calendar.MINUTE), true);//24 hour time
                timePicker.setTitle("Select end time");
                timePicker.show();
            }
        });
    }

    /**
     * Sets the value of the text fields to reflect the current date and time
     */
    private void initToCurrentTime()
    {
        EditText startTimeBox = (EditText)findViewById(R.id.text_startTime);
        startTimeBox.setText(startDate.getHours() + ":" + startDate.getMinutes());

        EditText endTimeBox = (EditText)findViewById(R.id.text_endTime);
        endTimeBox.setText(endDate.getHours() + ":" + endDate.getMinutes());

        EditText startDateBox = (EditText)findViewById(R.id.text_startDate);
        startDateBox.setText((startDate.getMonth() + 1) + "/" + startDate.getDate() + "/" + startDate.getYear());

        EditText endDateBox = (EditText)findViewById(R.id.text_endDate);
        endDateBox.setText((endDate.getMonth() + 1) + "/" + endDate.getDate() + "/" + endDate.getYear());
    }
}
