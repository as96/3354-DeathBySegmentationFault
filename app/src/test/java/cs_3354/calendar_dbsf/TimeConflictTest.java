package cs_3354.calendar_dbsf;

import org.junit.Before;
import org.junit.Test;

/**
 * Created by Lee on 12/6/17.
 */

public class TimeConflictTest
{
    EventListManager test1;
    EventListManager test2;
    private Event e;

    @Before
    public void initialize()
    {
        test1 = EventListManager.getInstance();
        test2 = EventListManager.getInstance();
    }

    @Test
    public void testConflicts()
    {
        test1.addEvent(e);
        test2.addEvent(e);

    }


}
