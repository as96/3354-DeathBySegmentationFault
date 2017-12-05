package cs_3354.calendar_dbsf;

import android.app.Application;
import android.content.Context;

/**
 * This class is used to keep track of the application context
 * It allows EventListManager to read and write to a file
 * Created by grant on 12/5/2017.
 */

public class App extends Application
{
    private static Context context;

    public static Context getContext()
    {
        return context;
    }

    public static void setContext(Context c)
    {
        context = c;
    }
}
