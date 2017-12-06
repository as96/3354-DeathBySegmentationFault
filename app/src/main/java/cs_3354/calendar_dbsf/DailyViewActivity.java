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
import android.util.Log;
import android.view.View;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static java.util.Locale.getDefault;

/**
 * Handles the creation of the DailyViewActivity
 * A long containing the date in millis must be contained in the savedInstanceState.
 */
public class DailyViewActivity extends AppCompatActivity
{

    private static final int NUM_PAGES = 100000;
    private ViewPager mPager;
    private static PagerAdapter mPagerAdapter;
    long savedDate;
    public static final String sDate = "sDate";
    private static final long MILLIS_IN_A_DAY = (1000 * 60 * 60 * 24);
    static Toolbar toolbar;
    static Context context;

    /**
     * Handles the initial setup if the DailyViewActivity
     * @param savedInstanceState saves the Instance state of the activity
     */
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        context = this;
        savedDate = new Date().getTime();
        if(getIntent() != null)
        {
            if(getIntent().hasExtra(sDate))
            {
                savedDate = getIntent().getLongExtra(sDate, savedDate);
            }
        }
        setContentView(R.layout.activity_daily_view);

        mPager = (ViewPager)findViewById(R.id.daypager);
        mPagerAdapter = new DayPagerAdapter(getSupportFragmentManager());
        mPager.setAdapter(mPagerAdapter);
        mPager.setCurrentItem(50000);

    }

    /**
     * <-->TODO:Is this right?</-->
     * Gets the fragment state pager adapter being used
     * @return FragmentStatePagerAdapter being used
     */
    public static FragmentStatePagerAdapter getAdapter()
    {
        return (FragmentStatePagerAdapter)mPagerAdapter;
    }

    /**
     * Handles when the user has pressed the back key
     */
    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
    }

    /**
     * An inline class that creates the DayPagerAdapter that permits swiping between fragments
     */
    private class DayPagerAdapter extends FragmentStatePagerAdapter
    {
        /**
         * Constructor for the DayPagerAdapter.
         * @param fm a FragmentManager that is used the superclass's constructor
         */
        public DayPagerAdapter(FragmentManager fm)
        {
            super(fm);
        }

        /**
         * Gets the data item at the specified position in the data set. In this case, a fragment
         * @param position the position of the fragment
         * @return the fragment as the specified position
         */
        @Override
        public Fragment getItem(int position)
        {
            long dailyDate = savedDate;
            dailyDate += (position - 50000) * MILLIS_IN_A_DAY;
            Bundle dateBundle = new Bundle();
            dateBundle.putLong("date", dailyDate);
            DailyViewFragment dailyViewFragment = new DailyViewFragment();
            dailyViewFragment.setArguments(dateBundle);
            return dailyViewFragment;
        }

        /**
         * <-->TODO:Is this right?</-->
         * @return the number of total pages the fragments use
         */
        @Override
        public int getCount(){
            return NUM_PAGES;
        }
    }


    /**
     * Creates an Intent to go to the event creation activity
     * @param v a view that is associated with this action
     */
    public void startCreateEventActivity(View v)
    {
        Intent intent = new Intent(DailyViewActivity.this, CreateEventActivity.class);
        intent.putExtra("date", savedDate);
        DailyViewActivity.this.startActivity(intent);
    }
}
