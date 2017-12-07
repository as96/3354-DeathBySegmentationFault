package cs_3354.calendar_dbsf;

import android.util.Log;

import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;

import static org.junit.Assert.*;

/**
 * Created by Trent on 12/5/2017.
 *
 * The DailyViewActivity has a pager with 100000 pages.
 * The 50000th page should be the current date, with each page i being the day
 * represented by the 50000th page's day minus (50000-i).
 */
public class DailyViewFragmentPositionerTest
{
    Calendar cal1;
    Calendar cal2;
    long testedDate;

    @Before
    public void setup()
    {
        cal1 = Calendar.getInstance();
        cal1.set(Calendar.HOUR_OF_DAY, 0);
        cal1.set(Calendar.MINUTE, 0);
        cal1.set(Calendar.SECOND, 0);
        cal2 = Calendar.getInstance();
        cal2.setTimeInMillis(cal2.getTimeInMillis() + DailyViewFragmentPositioner.MILLIS_IN_A_DAY);
        testedDate = DailyViewFragmentPositioner.getFragmentPosition(50000);
    }

    public void addDays(int numDays)
    {
        cal1.setTimeInMillis(cal1.getTimeInMillis() + numDays * DailyViewFragmentPositioner.MILLIS_IN_A_DAY);
        cal2.setTimeInMillis(cal2.getTimeInMillis() + numDays * DailyViewFragmentPositioner.MILLIS_IN_A_DAY);
    }

    @Test
    public void testTooSmall() throws Exception
    {
        addDays(-100000);
        testedDate = DailyViewFragmentPositioner.getFragmentPosition(-50000);
        assertTrue("Tested long is not smallest" + testedDate, testedDate == 0);
    }

    @Test
    public void testSmallest() throws Exception
    {
        addDays(-50000);
        testedDate = DailyViewFragmentPositioner.getFragmentPosition(0);
        assertTrue("Tested long is out of range: " + testedDate,
                testedDate > cal1.getTimeInMillis() && testedDate < cal2.getTimeInMillis());
    }

    @Test
    public void testYesterday() throws Exception
    {
        addDays(-1);
        testedDate = DailyViewFragmentPositioner.getFragmentPosition(49999);
        assertTrue("Tested long is out of range: " + testedDate,
                testedDate > cal1.getTimeInMillis() && testedDate < cal2.getTimeInMillis());
    }

    @Test
    public void testToday() throws Exception
    {
        testedDate = DailyViewFragmentPositioner.getFragmentPosition(50000);
        assertTrue("Tested long is out of range: " + testedDate,
                testedDate > cal1.getTimeInMillis() && testedDate < cal2.getTimeInMillis());
    }

    @Test
    public void testTomorrow() throws Exception
    {
        addDays(1);
        testedDate = DailyViewFragmentPositioner.getFragmentPosition(50001);
        assertTrue("Tested long is out of range: " + testedDate,
                testedDate > cal1.getTimeInMillis() && testedDate < cal2.getTimeInMillis());
    }

    @Test
    public void testLargest() throws Exception
    {
        addDays(49999);
        testedDate = DailyViewFragmentPositioner.getFragmentPosition(99999);
        assertTrue("Tested long is out of range: " + testedDate,
                testedDate > cal1.getTimeInMillis() && testedDate < cal2.getTimeInMillis());
    }

    @Test
    public void testTooLarge() throws Exception
    {
        addDays(100000);
        testedDate = DailyViewFragmentPositioner.getFragmentPosition(150000);
        assertTrue("Tested long is not largest" + testedDate, testedDate == 100000);
    }

    @Test
    public void getFragmentPosition() throws Exception
    {
        testTooSmall();
        testSmallest();
        testYesterday();
        testToday();
        testTomorrow();
        testLargest();
        testTooLarge();
    }

}