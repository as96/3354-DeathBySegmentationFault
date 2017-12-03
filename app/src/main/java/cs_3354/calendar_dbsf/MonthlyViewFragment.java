package cs_3354.calendar_dbsf;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 *
 * Created by Alec and Hajung
 */
public class MonthlyViewFragment extends Fragment {

    public MonthlyViewFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_monthly_view, container, false);
        //This is where we set up the calendar
        return v;
    }

    public void setTheme(View v)
    {
       Intent intent = new Intent(v.getContext(), themeActivity.class);
       startActivity(intent);

    }

}
