package edu.iis.mto.time;

import org.junit.BeforeClass;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderTest {

    private Order order;
    private static Clock clock;

    @BeforeAll static void start() {
        clock = new fakeSystemClock();
    }

    @BeforeEach void prep() {
        order = new Order(clock);
    }

    @Test void isOrderExpiredWhenConfirmedTooLate() {
        order.addItem(new OrderItem());
        order.submit();
        clock.setDate(clock.currentTime().plusHours(25));
        assertThrows(OrderExpiredException.class, () -> order.confirm());
        assertEquals(Order.State.CANCELLED, order.getOrderState());
    }

    @Test void isOrderStateConfirmedWhenConfirmedInTime() {
        order.addItem(new OrderItem());
        order.submit();
        order.confirm();
        assertEquals(Order.State.CONFIRMED, order.getOrderState());
    }
}
