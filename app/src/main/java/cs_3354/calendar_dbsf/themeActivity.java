package cs_3354.calendar_dbsf;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import java.util.ArrayList;
import java.util.List;

public class themeActivity extends AppCompatActivity {

    List<Theme> tList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_theme);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Select a theme.");
        setSupportActionBar(toolbar);

        for(Theme t: tList)
        {

        }





    }
}
