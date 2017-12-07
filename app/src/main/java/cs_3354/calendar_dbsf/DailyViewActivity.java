package cs_3354.calendar_dbsf;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

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
    static Context context;

    /**
     * Creates pages of DailyViewFragment to display the daily views.
     * @param savedInstanceState saves the Instance state of the activity
     */
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        context = this;
        if(getIntent() != null)
        {
            if(getIntent().hasExtra(sDate))
            {
                savedDate = getIntent().getLongExtra(sDate, savedDate);
            }
        }
        setContentView(R.layout.activity_daily_view);

        mPager = findViewById(R.id.daypager);
        mPagerAdapter = new DayPagerAdapter(getSupportFragmentManager());
        mPager.setAdapter(mPagerAdapter);
        mPager.setCurrentItem(50000);

    }

    /**
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
         * @param fm The FragmentManager for the pager's fragments
         */
        public DayPagerAdapter(FragmentManager fm)
        {
            super(fm);
        }

        /**
         * Gets the data item at the specified position in the data set. In this case, a fragment
         * @param position the position of the fragment
         * @return the fragment at the specified position
         */
        @Override
        public Fragment getItem(int position)
        {
            long dailyDate = savedDate;
            dailyDate += DailyViewFragmentPositioner.getFragmentPosition(position);
            Bundle dateBundle = new Bundle();
            dateBundle.putLong("date", dailyDate);
            DailyViewFragment dailyViewFragment = new DailyViewFragment();
            dailyViewFragment.setArguments(dateBundle);
            return dailyViewFragment;
        }

        /**
         * @return the number of total pages the in the pager.
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
