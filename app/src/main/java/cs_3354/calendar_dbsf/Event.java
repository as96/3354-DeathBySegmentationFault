package cs_3354.calendar_dbsf;

import android.content.Context;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;

public class Event implements Comparable<Event>, Serializable
{
    Date startDate;
    Date endDate;
    String name;
    String eventType;
    Alarm alarm;

    public Event(Date startDate, Date endDate, String name, String eventType, Context c)
    {
        this.startDate = startDate;
        this.endDate = endDate;
        this.name = name;
        this.eventType = eventType;
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTimeInMillis(startDate.getTime());
        alarm = new Alarm(cal, c, this);
    }

    public Event(Date startDate, Date endDate, String name, String eventType, int repeat, Context c)
    {
        this.startDate = startDate;
        this.endDate = endDate;
        this.name = name;
        this.eventType = eventType;
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTimeInMillis(startDate.getTime());
        alarm = new Alarm(cal, repeat*DailyViewFragmentPositioner.MILLIS_IN_A_DAY, c, this);
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
        return this.getStartDate().compareTo(other.getStartDate());
    }

    public Date getEndDate()
    {
        return endDate;
    }

    public String getName()
    {
        return name;
    }

    public Date getStartDate() { return startDate; }

    public void setStart(Date start)
    {
        this.startDate = start;
    }

    public void setEnd(Date end)
    {
        this.endDate = end;
    }

    public void setType(String type)
    {
        this.eventType = type;
    }

}
