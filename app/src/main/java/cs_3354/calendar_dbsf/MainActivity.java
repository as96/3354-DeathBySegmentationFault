package cs_3354.calendar_dbsf;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.CalendarView;
import android.widget.ImageButton;
import android.widget.ImageView;

import junit.framework.Assert;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

//MainActivity acts as "MonthlyViewActivity" since it is
public class MainActivity extends FragmentActivity {

    private static final int NUM_PAGES = 100000;
    private ViewPager mPager;
    private PagerAdapter mPagerAdapter;

    long savedDate;
    private static final long MILLIS_IN_A_DAY = (1000 * 60 * 60 * 24);
    static Context context;

    ImageView imageV;
    ImageButton themeButton;


    Theme theme;
    Image calImage;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        //setContentView(R.layout.activity_main);
        setContentView(R.layout.fragment_monthly_view);

        imageV = (ImageView) findViewById(R.id.calImageView);
        themeButton = (ImageButton) findViewById(R.id.themeButton);

        //String currentMonth = Integer.toString(mPager.getCurrentItem());

        //imageV.setImageResource(getDrawable(this, currentMonth));

        CustomCalendarView mView = (CustomCalendarView) findViewById(R.id.custom_calendar);
        mPager = (ViewPager)findViewById(R.id.monthPager);
        mPagerAdapter = new MonthPagerAdapter(getSupportFragmentManager());
        mPager.setAdapter(mPagerAdapter);
        mPager.setCurrentItem(50000);






//        Intent intent = new Intent(this, DailyViewActivity.class);
//        startActivity(intent);
    }

    //Need methods that utilize setUpCalendarAdapter and change the month upon swipe


    //Will draw the calendar image
    public static int getDrawable(Context context, String name)
    {
        Assert.assertNotNull(context);
        Assert.assertNotNull(name);

        return context.getResources().getIdentifier(name, "drawable", context.getPackageName());
    }

    //Swaps to the theme selection activity when the settings button is pressed
    public void setTheme(View v)
    {
        Intent intent = new Intent(this, themeActivity.class);
        startActivity(intent);

    }

    private class MonthPagerAdapter extends FragmentStatePagerAdapter
    {
        public MonthPagerAdapter(FragmentManager fm)
        {
            super(fm);
        }

        @Override
        public Fragment getItem(int position)
        {

            MonthlyViewFragment monthlyViewFragment = new MonthlyViewFragment();
            return monthlyViewFragment;
        }

        @Override
        public int getCount()
        {
            return NUM_PAGES;
        }
    }
}
