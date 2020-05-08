package edu.iis.mto.time;

import org.joda.time.DateTime;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderTest
{
    private static DateTime time;
    private static SystemClock systemClock;
    private static FakeClock fakeClock;

    @BeforeAll
    static void init()
    {
        time = new DateTime();
        systemClock = new SystemClock();
        fakeClock = new FakeClock();
    }

    @Test
    void ConfirmOrderJustAfterSubmitTest()
    {
        Order order = new Order(systemClock);
        order.addItem(new OrderItem());

        order.submit();
        assertDoesNotThrow(order::confirm);
    }

    @Test
    void ConfirmAndSubmitAtSameTimeTest()
    {
        Order order = new Order(fakeClock);
        order.addItem(new OrderItem());

        fakeClock.setTime(time);
        order.submit();
        assertDoesNotThrow(order::confirm);
    }

    @Test
    void Confirm24HoursAfterSubmitTest()
    {
        Order order = new Order(fakeClock);
        order.addItem(new OrderItem());

        fakeClock.setTime(time);
        order.submit();

        fakeClock.setTime(time.plusHours(24));
        assertDoesNotThrow(order::confirm);
    }
}