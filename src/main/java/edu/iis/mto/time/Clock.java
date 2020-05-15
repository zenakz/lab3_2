package edu.iis.mto.time;

import org.joda.time.DateTime;

import java.time.Instant;
import java.time.ZoneId;

public interface Clock {
    public DateTime currentTime();
    public void setDate(DateTime dateTime);
}

