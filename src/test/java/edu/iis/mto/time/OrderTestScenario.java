package edu.iis.mto.time;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class OrderTestScenario {

    private Clock mockedClock;


    @BeforeEach
    void setUp() {
        mockedClock = Mockito.mock(Clock.class);
    }

    @Test
    void orderConfirmationRequestedMoreThan24hoursAfterOrderSubmissionShouldEndUpWithOrderExpiredExceptionAndCancelledStatus() {

    }

    @Test
    void orderConfirmationRequestedLessThan24HoursAfterOrderSubmissionShouldNotEndUpWithOrderExpiredExceptionAndCancelledStatus() {

    }

    @Test
    void orderConfirmationRequestedBeforeOrderSubmissionShouldEndUpWithOrderStateException() {

    }

    @Test
    void orderConfirmationRequestedOneMinuteBeforeOrderExpirationShouldNotEndUpWithOrderExpiredExceptionAndCancelledState() {

    }

    @Test
    void orderConfirmationRequestedOneMinuteAfterOrderExpirationShouldNotEndUpWithOrderExpiredExceptionAndCancelledStatus() {

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
