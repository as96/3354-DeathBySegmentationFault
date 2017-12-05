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
import android.util.Log;
import android.util.Xml;
import android.widget.ImageButton;
import android.widget.ImageView;
import junit.framework.Assert;

import org.xmlpull.v1.XmlPullParser;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


//MainActivity acts as "MonthlyViewActivity" since it is
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

        //setContentView(R.layout.activity_main);
        setContentView(R.layout.fragment_monthly_view);
        //CustomCalendarView c = new CustomCalendarView(context);
        cal = new GregorianCalendar();
        cal.setTimeInMillis(new Date().getTime());
        long savedDate = new Date().getTime();
        Bundle bundle = new Bundle();
        bundle.putLong("date", savedDate);

        Intent intent = new Intent(this, MonthlyViewFragment.class);
        intent.putExtras(bundle);
//        startActivity(intent);



        imageV = (ImageView) findViewById(R.id.calImageView);

        //String currentMonth = Integer.toString(mPager.getCurrentItem());

        //imageV.setImageResource(getDrawable(this, currentMonth));

        CustomCalendarView mView = (CustomCalendarView) findViewById(R.id.custom_calendar);

        imageV.getDrawable();
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

    private class MonthPagerAdapter extends FragmentStatePagerAdapter
    {
        public MonthPagerAdapter(FragmentManager fm)
        {
            super(fm);
        }

        @Override
        public Fragment getItem(int position)
        {
            cal.add(Calendar.MONTH, position - 50000);
            Bundle bundle = new Bundle();
            bundle.putLong("date", cal.getTimeInMillis());
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
}
