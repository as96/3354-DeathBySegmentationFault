package cs_3354.calendar_dbsf;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ToggleButton;

import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by Trent on 12/5/2017.
 */

public class EditEvent extends DialogFragment
{

    Date startDate;
    Date endDate;
    String name;
    String type;
    final EventDatePicker eventStartDatePicker = new EventDatePicker();
    final EventTimePicker eventStartTimePicker = new EventTimePicker();
    final EventDatePicker eventEndDatePicker = new EventDatePicker();
    final EventTimePicker eventEndTimePicker = new EventTimePicker();
    private Event oldEvent, newEvent;
    EditText eventName, eventType, repeatInterval;
    ToggleButton isRepeating;
    Boolean toggled;
    private long eventTime;
    private DailyViewFragment fragment;
    private DeleteEvent deleteEvent;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {

        eventTime = getArguments().getLong("date time");
        EventListManager eventListManager = EventListManager.getInstance();
        Event[] events = eventListManager.getAllEvents();
        for (int i = 0; i < events.length; i++) {
            if (events[i].getStartDate().getTime() == eventTime)
                oldEvent = events[i];
        }


        final LinearLayout layout = new LinearLayout(getContext());
        layout.setOrientation(LinearLayout.VERTICAL);

        Button startDateButton = new Button(getContext());
        startDateButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v)
            {
                eventStartDatePicker.show(getActivity().getFragmentManager(), "eventDatePicker");
                eventStartTimePicker.show(getActivity().getFragmentManager(), "eventTimePicker");
            }
        });
        startDateButton.setText("Start date");
        layout.addView(startDateButton);

        Button endDateButton = new Button(getContext());
        endDateButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v)
            {
                eventEndDatePicker.show(getActivity().getFragmentManager(), "eventDatePicker");
                eventEndTimePicker.show(getActivity().getFragmentManager(), "eventTimePicker");
            }
        });

        endDateButton.setText("End date");
        layout.addView(endDateButton);

        eventName = new EditText(getContext());
        eventName.setSingleLine();
        eventName.setHint(oldEvent.getName());
        layout.addView(eventName);

        eventType = new EditText(getContext());
        eventType.setSingleLine();
        eventType.setHint(oldEvent.eventType);
        layout.addView(eventType);

        repeatInterval = new EditText(getContext());
        repeatInterval.setHint("Repeat Interval (dates)");

        toggled = false;
        isRepeating = new ToggleButton(getContext());
        isRepeating.setText("Repeating");
        isRepeating.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b)
                {
                    layout.addView(repeatInterval);
                    toggled = true;
                } else {
                    layout.removeView(repeatInterval);
                    toggled = false;
                }
            }
        });
        layout.addView(isRepeating);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity())
                .setIcon(R.drawable.notification_icon)
                .setTitle("Edit Event")
                .setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                startDate = new Date(eventStartDatePicker.getDate());
                                startDate.setHours(eventStartTimePicker.getHour());
                                startDate.setMinutes(eventStartTimePicker.getMinute());
                                endDate = new Date(eventEndDatePicker.getDate());
                                endDate.setHours(eventEndTimePicker.getHour());
                                endDate.setMinutes(eventStartTimePicker.getMinute());
                                name = eventName.getText().toString();
                                type = eventType.getText().toString();
                                int interval = 0;
                                if (!toggled) {
                                    newEvent = new Event(startDate, endDate, name, type, getContext());
                                }
                                else
                                {
                                    interval = Integer.decode(repeatInterval.getText().toString());
                                    newEvent = new Event(startDate, endDate, name, type, interval, getContext());
                                }
                                EventListManager eventListManager = EventListManager.getInstance();
                                eventListManager.addEvent(newEvent);
                                GregorianCalendar gregCal = new GregorianCalendar();
                                gregCal.setTimeInMillis(startDate.getTime());
                                eventListManager.removeEvent(oldEvent);
                                deleteEvent.setTime(eventTime);
                                deleteEvent.deleteButton();
                                fragment.addEventButton(getActivity(), newEvent);
                            }
                        })
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        });
        builder.setView(layout);
        return builder.create();
    }

    public void setFields(DailyViewFragment f, DeleteEvent d)
    {
        fragment = f;
        deleteEvent = d;
    }

}
