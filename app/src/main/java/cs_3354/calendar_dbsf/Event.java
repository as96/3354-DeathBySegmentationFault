package cs_3354.calendar_dbsf;

public class Event
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