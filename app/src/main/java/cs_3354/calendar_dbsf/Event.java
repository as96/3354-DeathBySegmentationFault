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
    transient Alarm alarm;
    Boolean alarmSet;

    private static final long serialVersionUID = 42L;

    /**
     * Event constructor takes in the following parameters:
     * @param startDate the start date of the event
     * @param endDate the end date of the event
     * @param name the name of the event
     * @param eventType the type of the event
     * @param c the context of the event
     */
    public Event(Date startDate, Date endDate, String name, String eventType, Context c)
    {
        this.startDate = startDate;
        this.endDate = endDate;
        this.name = name;
        this.eventType = eventType;
        alarmSet = false;
        GregorianCalendar cal1 = new GregorianCalendar();
        cal1.setTimeInMillis(startDate.getTime());
        if (!startDate.before(new Date()))
        {
            alarm = new Alarm(cal1, c, this);
            alarmSet = true;
        }
    }

    /**
     * event constructor takes in the following parameters:
     * @param startDate the start date of the event
     * @param endDate the end date of the event
     * @param name the name of the event
     * @param eventType the type of the event
     * @param repeat <-->TODO: define this</-->
     * @param c the context of the event
     */
    public Event(Date startDate, Date endDate, String name, String eventType, int repeat, Context c)
    {
        this.startDate = startDate;
        this.endDate = endDate;
        this.name = name;
        this.eventType = eventType;
        GregorianCalendar cal2 = new GregorianCalendar();
        cal2.setTimeInMillis(startDate.getTime());
        alarmSet = false;
        if (!startDate.before(new Date()))
        {
            alarm = new Alarm(cal2, repeat * DailyViewFragmentPositioner.MILLIS_IN_A_DAY, c, this);
            alarmSet = true;
        }
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

    /**
     *
     * @return the end date of the event
     */
    public Date getEndDate()
    {
        return endDate;
    }

    /**
     *
     * @return the name of the event
     */
    public String getName()
    {
        return name;
    }

    /**
     *
     * @return the start date of the event
     */
    public Date getStartDate() { return startDate; }

    /**
     *
     * @param start the start date of the event
     */
    public void setStart(Date start)
    {
        this.startDate = start;
    }
    
    /**
     *
     * @param end the end date of the event
     */
    public void setEnd(Date end)
    {
        this.endDate = end;
    }
    
    /**
     *
     * @param type the type of the event
     */
    public void setType(String type)
    {
        this.eventType = type;
    }

}
