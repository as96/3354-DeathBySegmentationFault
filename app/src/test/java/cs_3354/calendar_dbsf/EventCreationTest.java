package cs_3354.calendar_dbsf;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by asala on 12/6/2017.
 */

public class EventCreationTest
{
    String name;
    String type;
    CreateEventActivity createEventActivity;
    EventListManager i;

    @Before
    public void initialize()
    {
        createEventActivity = new CreateEventActivity();
        i = EventListManager.getInstance();
    }

    @Test
    public void testNameLength()
    {
        name = "Event 1";
        assertEquals(true, createEventActivity.checkNameLength(name));
    }

    @Test
    public void testTypeLength()
    {
        type = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        assertEquals(false, createEventActivity.checkNameLength(name));

    }


}
