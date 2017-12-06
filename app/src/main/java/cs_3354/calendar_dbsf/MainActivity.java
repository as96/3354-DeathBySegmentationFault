package cs_3354.calendar_dbsf;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import junit.framework.Assert;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;


//MainActivity acts as "MonthlyViewActivity" since it is
public class MainActivity extends FragmentActivity {

    private static final int NUM_PAGES = 100000;
    private ViewPager mPager;
    private PagerAdapter mPagerAdapter;

    long savedDate;
    static Context context;

    ImageView imageV;
    ImageButton themeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        setContentView(R.layout.activity_daily_view);
        Intent intent = new Intent(this, DailyViewActivity.class);
        Date d = new Date();
        Event e = new Event(d, new Date(), "Dentist's Appointment", "Appointment", this);
        EventListManager eventListManager = EventListManager.getInstance();
        eventListManager.addEvent(e);
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTimeInMillis(d.getTime());
        Alarm a = new Alarm(cal, this, e);
        startActivity(intent);

        /*fragmentDates = new HashMap<>();

        imageV = (ImageView) findViewById(R.id.calImageView);
        themeButton = (ImageButton) findViewById(R.id.themeButton);

        App.setContext(this);

        //String currentMonth = Integer.toString(mPager.getCurrentItem());

        //imageV.setImageResource(getDrawable(this, currentMonth));

        CustomCalendarView mView = (CustomCalendarView) findViewById(R.id.custom_calendar);
        imageV.getDrawable();
        mPager = (ViewPager)findViewById(R.id.monthPager);
        mPagerAdapter = new MonthPagerAdapter(getSupportFragmentManager());
        mPager.setAdapter(mPagerAdapter);
        mPager.setCurrentItem(50000);






//        Intent intent = new Intent(this, DailyViewActivity.class);
//        startActivity(intent);*/
    }

    //Need methods that utilize setUpCalendarAdapter and change the month upon swipe


    //Will draw the calendar image
   /* public static int getDrawable(Context context, String name)
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
            GregorianCalendar date = new GregorianCalendar();
            date.add(Calendar.MONTH, (50000 - position));
            savedDate = date.getTimeInMillis();
            MonthlyViewFragment monthlyViewFragment = new MonthlyViewFragment();
            fragmentDates.put(monthlyViewFragment, savedDate);
            return monthlyViewFragment;
        }

        @Override
        public int getCount()
        {
            return NUM_PAGES;
        }
    }*/
}
