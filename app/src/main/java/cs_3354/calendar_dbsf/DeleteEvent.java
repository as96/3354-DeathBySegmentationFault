package cs_3354.calendar_dbsf;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import java.util.Date;
import java.util.HashMap;

/**
 * Created by Trent on 12/3/2017.
 * This class handles all functions relating to event deletion
 */
public class DeleteEvent extends DialogFragment
{

    private Button eventButton;
    private LinearLayout layout;
    private Event event;
    private EditEvent editEventDialog;
    long time;
    public DailyViewFragment dailyViewFragment;
    public DeleteEvent currentInstance;
    static HashMap<Event, DeleteEvent> deleteMapper = new HashMap<>();

    /**
     * <-->TODO:Is this right?</-->
     * Creates a new instance of a DeleteEvent dialog
     * @param date a date as a long, which will be passed along as the event time
     * @return A dialog box that contains information of the event to delete
     */
    public static DeleteEvent newInstance(long date)
    {
        DeleteEvent dialog = new DeleteEvent();
        Bundle args = new Bundle();
        args.putLong("eventTime", date);
        dialog.setArguments(args);
        return dialog;
    }

    /**
     *
     * @param l
     * @param b
     * @param frag
     */
    public void setParams(LinearLayout l, Button b, DailyViewFragment frag)
    {
        eventButton = b;
        layout = l;
        dailyViewFragment = frag;
    }

    /**
     * Builds a custom dialog container
     * @param savedInstanceState The last saved instance state of the Fragment, or null if this is a freshly created Fragment.
     * @return Return a new Dialog instance to be displayed by the Fragment.
     */
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        Bundle bundle = getArguments();
        time = bundle.getLong("eventTime");
        currentInstance = this;
        event = getEvent();
        deleteMapper.put(event, this);
        return new AlertDialog.Builder(getActivity())
                .setIcon(R.drawable.notification_icon)
                .setTitle(event.getName())
                .setPositiveButton("Delete",
                        new DialogInterface.OnClickListener()
                        {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i)
                            {
                                deleteEvent();
                            }
                        })
                .setNegativeButton("Edit",
                        new DialogInterface.OnClickListener()
                        {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i)
                            {
                                Bundle bundle = new Bundle();
                                bundle.putLong("date time", event.getStartDate().getTime());
                                editEventDialog = new EditEvent();
                                editEventDialog.setArguments(bundle);
                                editEventDialog.setFields(dailyViewFragment, currentInstance);
                                editEventDialog.show(getActivity().getFragmentManager(), "dialog");
                            }
                        })
                .setNeutralButton("Cancel",
                        new DialogInterface.OnClickListener()
                        {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i)
                            {
                                dialogInterface.cancel();
                            }
                        })
                .create();
    }

    /**
     * <-->TODO:Is this right? Why are we storing events in an array?</-->
     * Gets the event to be deleted
     * @return
     */
    public Event getEvent()
    {
        EventListManager eventListManager = EventListManager.getInstance();
        Event[] events = eventListManager.getEventsBetween(new Date(time - 1), new Date(time + 1));
        return events[0];
    }

    /**
     * Deletes the event from the EventListManager
     */
    public void deleteEvent()
    {
        deleteButton();
        EventListManager eventListManager = EventListManager.getInstance();
        eventListManager.removeEvent(event);
    }

    /**
     * Sets the time
     * @param t a date object that is in the form of a long
     */
    public void setTime(long t)
    {
        time = t;
    }

    /**
     * Removes the event button when the event is deleted
     */
    public void deleteButton()
    {
        layout.removeView(eventButton);
    }
}
