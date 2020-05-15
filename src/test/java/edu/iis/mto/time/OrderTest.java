package edu.iis.mto.time;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderTest {

    private Order order;
    private static Clock clock;

    @BeforeEach void prep() {
        clock = new fakeSystemClock();
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

    @Test void doesConfirmUnsubmitedOrderThrowException() {
        order.addItem(new OrderItem());
        assertThrows(OrderStateException.class, () -> order.confirm());
    }

    @Test void doesRealizeUnconfirmedOrderThrowException() {
        order.addItem(new OrderItem());
        order.submit();
        assertThrows(OrderStateException.class, () -> order.realize());
    }

    @Test void isOrderStateRealizedAfterRealize() {
        order.addItem(new OrderItem());
        order.submit();
        clock.setDate(clock.currentTime().plusHours(2));
        order.confirm();
        order.realize();
        assertEquals(Order.State.REALIZED, order.getOrderState());
    }

    @Test void isOrderExpiredWhenConfirmedAfterAYear() {
        order.addItem(new OrderItem());
        order.submit();
        clock.setDate(clock.currentTime().plusYears(1));
        assertThrows(OrderExpiredException.class, () -> order.confirm());
    }
}
