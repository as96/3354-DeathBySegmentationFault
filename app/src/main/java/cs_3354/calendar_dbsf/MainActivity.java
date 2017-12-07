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
import android.util.AttributeSet;
import android.util.Xml;
import android.view.View;
import android.widget.CalendarView;
import android.widget.ImageButton;
import android.widget.ImageView;
import junit.framework.Assert;
import org.xmlpull.v1.XmlPullParser;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


public class MainActivity extends FragmentActivity {

    private static final int NUM_PAGES = 100000;
    private ViewPager mPager;
    private PagerAdapter mPagerAdapter;
    GregorianCalendar cal;
    private static final long MILLIS_IN_A_DAY = (1000 * 60 * 60 * 24);
    static Context context;

    ImageView imageV;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cal = new GregorianCalendar();
        cal.setTimeInMillis(new Date().getTime());
        long savedDate = new Date().getTime();
        Bundle bundle = new Bundle();
        bundle.putLong("date", savedDate);

        App.setContext(this);

        CalendarView calendarView = (CalendarView)findViewById(R.id.calendarView);
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener()
        {
            @Override
            public void onSelectedDayChange(CalendarView view, int year,
                                            int month, int dayOfMonth)
            {
                Date selectedDate = new Date(year - 1900, month, dayOfMonth);

                Intent intent = new Intent(MainActivity.this, DailyViewActivity.class);
                intent.putExtra(DailyViewActivity.sDate, selectedDate.getTime());
                MainActivity.this.startActivity(intent);
            }
        });

        //Old way of scrolling through months
        /*
        mPager = (ViewPager)findViewById(R.id.monthPager);
        mPagerAdapter = new MonthPagerAdapter(getSupportFragmentManager());
        mPager.setAdapter(mPagerAdapter);
        mPager.setCurrentItem(5000);
        */

//        Intent intent = new Intent(this, DailyViewActivity.class);
//        startActivity(intent);*/
    }

    //Need methods that utilize setUpCalendarAdapter and change the month upon swipe

    public void createEvent(View v)
    {
        Intent intent = new Intent(MainActivity.this, CreateEventActivity.class);
        MainActivity.this.startActivity(intent);
    }

    //Will draw the calendar image
    public static int getDrawable(Context context, String name)
    {
        Assert.assertNotNull(context);
        Assert.assertNotNull(name);

        return context.getResources().getIdentifier(name, "drawable", context.getPackageName());
    }

    /*
    private class MonthPagerAdapter extends FragmentStatePagerAdapter
    {
        public MonthPagerAdapter(FragmentManager fm)
        {
            super(fm);
        }

        @Override
        public Fragment getItem(int position)
        {
            Calendar currentMonth = new GregorianCalendar();
            currentMonth.add(Calendar.MONTH, position - 5000);
            Bundle bundle = new Bundle();
            bundle.putLong("date", currentMonth.getTimeInMillis());
            MonthlyViewFragment monthlyViewFragment = new MonthlyViewFragment();
            monthlyViewFragment.setArguments(bundle);
            return monthlyViewFragment;
        }

        @Override
        public int getCount()
        {
            return NUM_PAGES;
        }
    }
    */
}
