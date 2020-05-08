package edu.iis.mto.time;

import org.joda.time.DateTime;

public class SystemClock implements Clock
{
    @Override
    public DateTime now()
    {
        return new DateTime();
    }
}
