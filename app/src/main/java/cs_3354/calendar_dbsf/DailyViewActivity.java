package cs_3354.calendar_dbsf;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static java.util.Locale.getDefault;

//A long containing the date in millis must be contained in the savedInstanceState.
public class DailyViewActivity extends AppCompatActivity
{

    private static final int NUM_PAGES = 100000;
    private ViewPager mPager;
    private PagerAdapter mPagerAdapter;
    long savedDate;
    private static final long MILLIS_IN_A_DAY = (1000 * 60 * 60 * 24);
    static Toolbar toolbar;
    static Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        context = this;
        savedDate = new Date().getTime();
        setContentView(R.layout.activity_daily_view);

        /*toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("");

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);*/

        mPager = (ViewPager)findViewById(R.id.daypager);
        mPagerAdapter = new DayPagerAdapter(getSupportFragmentManager());
        mPager.setAdapter(mPagerAdapter);
        mPager.setCurrentItem(50000);

        GregorianCalendar cal = new GregorianCalendar();
        //cal.setTimeInMillis(savedDate);

    }

    public static void setToolbarTitle(String s)
    {
        toolbar.setTitle(s);
    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
    }

    private class DayPagerAdapter extends FragmentStatePagerAdapter
    {
        public DayPagerAdapter(FragmentManager fm)
        {
            super(fm);
        }

        @Override
        public Fragment getItem(int position)
        {
            savedDate = new Date().getTime() + (position - 50000) * MILLIS_IN_A_DAY;
            Bundle dateBundle = new Bundle();
            dateBundle.putLong("date", savedDate);
            DailyViewFragment dailyViewFragment = new DailyViewFragment();
            dailyViewFragment.setArguments(dateBundle);
            return dailyViewFragment;
        }

        @Override
        public int getCount(){
            return NUM_PAGES;
        }
    }

    public void startCreateEventActivity(View v)
    {
        //TODO code is complete but will not work until this branch is merged
        /*
        Intent intent = new Intent(DailyViewActivity.this, CreateEventActivity.class);
        DailyViewActivity.this.startActivity(intent);
        */
    }
}
