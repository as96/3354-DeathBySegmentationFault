package cs_3354.calendar_dbsf;

import java.util.ArrayList;
import java.util.Date;

public class Event implements Comparable<Event>
{
    Date date;
    String name;
    int startTime;
    int endTime;
    ArrayList<Alarm> alarms;
    String eventType;

    public Event(int y, int m, int d, String name, int startTime,
                 int endTime, String eventType)
    {

    }

    /**
     * Compares the starting date and time of two events
     * Used for sorting algorithms
     *
     * @param other The other event being compared
     * @return 0 if event times are the same, negative int if the argument is later, positive int if the argument is earlier
     */
    public int compareTo(Event other)
    {
        return this.getDate().compareTo(other.getDate());
    }

    public Date getDate() { return date; }

    public String getName()
    {
        return name;
    }

    public int getStartTime()
    {
        return startTime;
    }

    public int getEndTime()
    {
        return endTime;
    }
}