package edu.iis.mto.time;

import org.joda.time.DateTime;

public class FakeClock implements Clock
{
    private DateTime time = new DateTime(0L);

    public void setTime(DateTime time)
    {
        this.time = time;
    }

    @Override
    public DateTime now()
    {
        return time;
    }
}
