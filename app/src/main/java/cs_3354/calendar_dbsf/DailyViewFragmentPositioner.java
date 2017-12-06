package cs_3354.calendar_dbsf;

import java.util.Date;

/**
 * Created by Trent on 12/5/2017.
 *
 *Handles the positioning of the DailyViewFragment\
 * <-->TODO:The getFragmentPosition method is never used, is this class really needed?</-->
 */
public class DailyViewFragmentPositioner
{

    public static final long MILLIS_IN_A_DAY = (1000 * 60 * 60 * 24);

    public static long getFragmentPosition(int pos)
    {
        if (pos < 0)
            return 0;
        else if (pos > 100000)
            return 100000;
        Date newDate = new Date();
        newDate.setTime(newDate.getTime() + (pos - 50000) * MILLIS_IN_A_DAY);
        return newDate.getTime();
    }
}
