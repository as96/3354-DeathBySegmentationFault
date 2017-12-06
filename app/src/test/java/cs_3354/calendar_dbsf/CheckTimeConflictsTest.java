package cs_3354.calendar_dbsf;

import android.test.InstrumentationTestCase;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;



/**
 * Created by asala on 12/6/2017.
 */

public class CheckTimeConflictsTest extends InstrumentationTestCase {

    final int NUM_EVENTS = 2;

    EventListManager tester;
    Event[] sampleEvents;

    @Before
    public void initialize()
    {
        tester = EventListManager.getInstance();
        sampleEvents = new Event[NUM_EVENTS];

//        //Create sample events for testing purposes and add them to sampleEvents
//        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
//        try
//        {
//            for(int i = 0; i < NUM_EVENTS; i++)
//            {
//                Date d1 = sdf.parse("11/" + i + "/2017");
//                Date d2 = sdf.parse("11/" + (int)(i + 1) + "/2017");
//
//
//                Event e = new Event(d1, d2, "Event " + i, "TestEvent", getInstrumentation().getContext());
//                sampleEvents[i] = e;
//            }
//        }
//        catch (ParseException e)
//        {
//            e.printStackTrace();
//        }
    }

    @Test
    public void conflicts2()
    {
        Date d1 = new Date("07/22/2017");
        Date d2 = new Date("07/22/2017");
        Date d3 = new Date("07/06/2017");
        Date d4 = new Date("07/06,/2017");

        Event e1 = new Event(d1, d2, "Event 1 Test", "testEvent", getInstrumentation().getContext());
        Event e2 = new Event(d3, d4, "Event 2 Test", "testEvent", getInstrumentation().getContext());
        sampleEvents[0] = e1;
        sampleEvents[1] = e2;

        assertTrue(tester.checkTimeConflicts(e1));

    }

    @Test
    public void conflicts1()
    {
        Date d1 = new Date("11/23/2017");
        Date d2 = new Date("11/24/2017");

        Event e1 = new Event(d1, d2, "Event 1 Test", "testEvent", getInstrumentation().getContext());
        Event e2 = new Event(d1, d2, "Event 2 Test", "testEvent", getInstrumentation().getContext());
        sampleEvents[0] = e1;
        sampleEvents[1] = e2;

        assertTrue(tester.checkTimeConflicts(e1));

    }


}
