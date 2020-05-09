package edu.iis.mto.time;

import org.joda.time.DateTime;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.MatcherAssert.assertThat;

class OrderTestScenario {

    private Clock mockedClock;
    private Order order;
    private DateTime testDateTime;
    private OrderItem item;

    @BeforeEach
    void setUp() {
        mockedClock = Mockito.mock(Clock.class);
        order = new Order();
        testDateTime = DateTime.now();
        item = Mockito.mock(OrderItem.class);
        order.setSystemClock(mockedClock);
        order.addItem(item);
    }

    @Test
    void orderConfirmationRequestedMoreThan24hoursAfterOrderSubmissionShouldEndUpWithOrderExpiredExceptionAndCancelledStatus() {
        Mockito.doReturn(testDateTime)
                .doReturn(testDateTime.plusHours(25))
                .when(mockedClock)
                .getCurrentDateTime();

        order.submit();

        assertThrows(OrderExpiredException.class, () -> order.confirm());

        var result = order.getOrderState();
        assertThat(result, equalTo(Order.State.CANCELLED));
    }

    @Test
    void orderConfirmationRequestedLessThan24HoursAfterOrderSubmissionShouldNotEndUpWithOrderExpiredExceptionAndCancelledStatus() {
        Mockito.doReturn(testDateTime)
                .doReturn(testDateTime.plusHours(23))
                .when(mockedClock)
                .getCurrentDateTime();

        order.submit();

        assertDoesNotThrow(() -> order.confirm());

        var result = order.getOrderState();
        assertThat(result, not(Order.State.CANCELLED));
    }

    @Test
    void orderConfirmationRequestedBeforeOrderSubmissionShouldEndUpWithOrderStateException() {
        assertThrows(OrderStateException.class, () -> order.confirm());
    }

    @Test
    void orderConfirmationRequestedOneMinuteBeforeOrderExpirationShouldNotEndUpWithOrderExpiredExceptionAndCancelledState() {
        Mockito.doReturn(testDateTime)
                .doReturn(testDateTime.plusMinutes(59).plusHours(23))
                .when(mockedClock)
                .getCurrentDateTime();

        order.submit();

        assertDoesNotThrow(() -> order.confirm());

        var result = order.getOrderState();
        assertThat(result, not(Order.State.CANCELLED));
    }

    @Test
    void orderConfirmationRequestedOneMinuteAfterOrderExpirationShouldNotEndUpWithOrderExpiredExceptionAndCancelledStatus() {
        Mockito.doReturn(testDateTime)
                .doReturn(testDateTime.plusMinutes(1).plusHours(24))
                .when(mockedClock)
                .getCurrentDateTime();

        order.submit();

        assertDoesNotThrow(() -> order.confirm());

        var result = order.getOrderState();
        assertThat(result, not(Order.State.CANCELLED));
    }

    @Test
    void orderConfirmationRequested59MinutesAfterOrderExpirationShouldNotEndUpWithOrderExpiredExceptionAndCancelledStatus() {

    }

    @Test
    void orderConfirmationRequestedNearlyInTheSameTimeAsOrderSubmissionShouldEndUpWithConfirmedStatus() {

    }

    @AfterEach
    void tearDown() {
    }
}
