package cs_3354.calendar_dbsf;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class maintains a sorted list of events.
 * It uses the singleton design pattern to ensure there cannot be multiple lists.
 *
 * Created by Grant M on 11/6/2017.
 */

public class EventListManager
{
    private final EventListManager INSTANCE = new EventListManager();

    private List<Event> events;

    private EventListManager()
    {
        events = new ArrayList<Event>();
    }

    /**
     * Adds an Event to the list of events
     * @param e the Event to be added
     */
    public void addEvent(Event e)
    {
        //TODO implement binary insertion
        events.add(e);
    }

    /**
     * Returns true if the given event overlaps with other events
     * @param e the event being checked
     * @return true if there are time conflicts, otherwise returns false
     */
    public boolean checkTimeConflicts(Event e)
    {
        //TODO write method
        return false;
    }

    /**
     * Determines whether an event exists within the list.
     * A helper method for removeEvent() and editEvent().
     *
     * @param e The event being checked
     * @return True if the event is in the list, false otherwise
     */
    public boolean containsEvent(Event e)
    {
        return events.contains(e);
    }

    /**
     * Edits an event that already exists
     * @param e the Event that will be edited
     * @param d the new Date of the event
     * @param sTime the new start time of the event
     * @param eTime the new end time of the event
     * @param alarms a list of alarms associated with the event
     * @param eType the type of the event
     */
    public void editEvent(Event e,
                          Date d,
                          int sTime,
                          int eTime,
                          ArrayList<Alarm> alarms,
                          String eType)
    {
        //TODO write method (Hajung)
    }

    /**
     * Takes in two dates and returns a list of any events that occur between the two dates
     * @param start the starting date
     * @param end the ending date
     * @return an array of Events that occur between the start and end date
     */
    public Event[] getEventsBetween(Date start, Date end)
    {
        //TODO write method
        return null;
    }

    /**
     * Returns a reference to the single instance of this class.
     * A necessary part of the singleton design pattern.
     * @return An EventListManager object
     */
    public EventListManager getInstance()
    {
        return INSTANCE;
    }

    /**
     * Removes an Event from the list of events
     * @param e the Event to be removed
     * @throws NullPointerException if the event is not found in the list
     */
    public void removeEvent(Event e) throws NullPointerException
    {
        if(containsEvent(e))
        {
            events.remove(e);
        }
        else
        {
            throw new NullPointerException ("Event does not exist in list");
        }
    }
}
