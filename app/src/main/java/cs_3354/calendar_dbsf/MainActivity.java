package cs_3354.calendar_dbsf;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GregorianCalendar c = new GregorianCalendar();
        c.setTimeInMillis(new Date().getTime() + 30000);
        new Alarm(c, this);
    }
}
