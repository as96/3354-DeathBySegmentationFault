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

/**
 * Created by Trent on 12/3/2017.
 */

public class DeleteEvent extends DialogFragment
{

    private Button event;
    private LinearLayout layout;

    public static DeleteEvent newInstance(long date)
    {
        DeleteEvent dialog = new DeleteEvent();
        Bundle args = new Bundle();
        args.putLong("eventTime", date);
        dialog.setArguments(args);
        return dialog;
    }

    public void setParams(LinearLayout l, Button b)
    {
        event = b;
        layout = l;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        return new AlertDialog.Builder(getActivity())
                .setIcon(R.drawable.notification_icon)
                .setTitle("Delete event?")
                .setPositiveButton("Yes",
                        new DialogInterface.OnClickListener()
                        {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i)
                            {
                                deleteEvent();
                            }
                        })
                .setNegativeButton("No",
                        new DialogInterface.OnClickListener()
                        {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i)
                            {
                            }
                        })
                .create();
    }

    public void deleteEvent()
    {
        layout.removeView(event);
        Bundle bundle = getArguments();
        long eventTime = bundle.getLong("eventTime");
        EventListManager eventListManager = EventListManager.getInstance();
        Event[] events = eventListManager.getEventsBetween(new Date(eventTime - 1), new Date(eventTime + 1));
        eventListManager.removeEvent(events[0]);
    }
}
