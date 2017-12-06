package cs_3354.calendar_dbsf;

import android.test.InstrumentationTestCase;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.assertTrue;

/**
 * A test class for EventListManager
 * Created by grant on 12/2/2017.
 */

public class EventListManagerTest extends InstrumentationTestCase
{
    final int NUM_EVENTS = 6;

    EventListManager tester;
    Event[] sampleEvents;

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


                Event e = new Event(d1, d2, "Event " + i, "TestEvent", getInstrumentation().getContext());
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
}
