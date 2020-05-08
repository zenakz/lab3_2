package edu.iis.mto.time;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderTest
{
    @Test
    void ConfirmOrderJustAfterSubmitTest()
    {
        Order order = new Order();
        order.addItem(new OrderItem());
        order.submit();
        assertDoesNotThrow(order::confirm);
    }
}