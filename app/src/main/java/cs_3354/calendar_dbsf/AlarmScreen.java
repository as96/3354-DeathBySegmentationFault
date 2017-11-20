package cs_3354.calendar_dbsf;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;

/**
 * Created by Trent on 11/6/2017.
 * Dialogue box that will show when an alarm is triggered.
 */

public class AlarmScreen extends DialogFragment
{

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(R.string.event_alarm)
                .setNegativeButton(R.string.dismiss, new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog, int id){
                        //User dismisses the alarm
                    }
                })
                .setNeutralButton(R.string.snooze, new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog, int id){
                        //User hits the snooze button
                    }
                });

        return builder.create();

    }
}
