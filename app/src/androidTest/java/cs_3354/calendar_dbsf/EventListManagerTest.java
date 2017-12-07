package cs_3354.calendar_dbsf;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.Assert;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import cs_3354.calendar_dbsf.Event;
import cs_3354.calendar_dbsf.EventListManager;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * A test class for EventListManager
 * Created by grant on 12/2/2017.
 */
@RunWith(AndroidJUnit4.class)
public class EventListManagerTest
{
    final int NUM_EVENTS = 6;

    EventListManager tester;
    Event[] sampleEvents;

    Context context;

    @Before
    public void setup()
    {
        context = InstrumentationRegistry.getContext();
    }

    @Before
    public void initialize()
    {
        tester = EventListManager.getInstance();
        sampleEvents = new Event[NUM_EVENTS];

        //Create sample events for testing purposes and add them to sampleEvents
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        try
        {
            for(int i = 0; i < NUM_EVENTS; i++)
            {
                Date d1 = sdf.parse("11/" + i + "/2017");
                Date d2 = sdf.parse("11/" + (int)(i + 1) + "/2017");


                Event e = new Event(d1, d2, "Event " + i, "TestEvent", context);
                sampleEvents[i] = e;
            }
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * Test that all events are added and sorted properly
     */
    @Test
    public void testAdd()
    {
        for(int i = 0; i < sampleEvents.length; i++)
        {
            tester.addEvent(sampleEvents[i]);
        }

        Event[] events = tester.getAllEvents();

        for(int i = 0; i < sampleEvents.length; i++)
        {
            assertTrue(tester.containsEvent(sampleEvents[i]));

            if(i != sampleEvents.length - 1)
            {
                assertTrue(events[i].getStartDate().before(events[i + 1].getStartDate()));
            }
        }
    }

    /**
     * Test the behavior of an empty event list
     */
    @Test
    public void testEmpty()
    {
        Assert.assertEquals(0, tester.getNumEvents());
    }

    /**
     * Test that an exception is thrown if trying to remove a nonexistant event
     */
    @Test
    public void testInvalidRemove()
    {
        try
        {
            tester.removeEvent(sampleEvents[0]);
            fail(); //If this line is reached, then the tester did not throw an exception like it should
        }
        catch(NullPointerException e)
        {

        }
    }

    /**
     * Test that the eventListManager saves and loads events properly
     */
    @Test
    public void testFileIO()
    {
        tester.addEvent(sampleEvents[0]);
        tester.writeToFile();
        tester.readFromFile();
        Assert.assertEquals(3, tester.getNumEvents());
    }
}
