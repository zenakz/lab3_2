package edu.iis.mto.time;

import org.joda.time.DateTime;

public class fakeSystemClock implements Clock {

    private DateTime dateTime;

    public fakeSystemClock() {
        dateTime = new DateTime();
    }

    @Override public DateTime currentTime() {
        return dateTime;
    }

    @Override public void setDate(DateTime dateTime) {
        dateTime = new DateTime();
    }
}
