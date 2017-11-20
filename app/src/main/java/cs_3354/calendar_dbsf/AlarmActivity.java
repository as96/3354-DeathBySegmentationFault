package cs_3354.calendar_dbsf;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

/**
 * Created by Trent on 11/18/2017.
 *
 * This activity exists only because AlarmScreen (a DialogFragment) cannot be triggered by
 * AlarmManager, so it needs an Activity whose sole purpose is to create it, which
 * AlarmManager would then trigger.
 */

public class AlarmActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        DialogFragment fragment = new AlarmScreen();
        fragment.show(getFragmentManager(), "alarm");
    }

}
