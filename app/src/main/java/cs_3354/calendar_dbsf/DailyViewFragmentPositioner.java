package cs_3354.calendar_dbsf;

import java.util.Date;

/**
 * Created by Trent on 12/5/2017.
 *
 *Gives in milliseconds the # of days before/after a given position in the pager
 */
public class DailyViewFragmentPositioner
{

    public static final long MILLIS_IN_A_DAY = (1000 * 60 * 60 * 24);

    /**
     * Performs the calculation described in the comment for the class
     * @return the # of days in milliseconds before/after the given position
     */
    public static long getFragmentPosition(int pos)
    {
        if (pos < 0)
            return 0;
        else if (pos > 100000)
            return 100000;
        Date newDate = new Date();
        return ((pos - 50000) * MILLIS_IN_A_DAY);
    }
}
