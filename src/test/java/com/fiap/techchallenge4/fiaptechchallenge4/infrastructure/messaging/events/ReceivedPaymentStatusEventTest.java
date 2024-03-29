package com.fiap.techchallenge4.fiaptechchallenge4.infrastructure.messaging.events;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.OffsetDateTime;

public class ReceivedPaymentStatusEventTest {

    @Test
    public void testReceivedPaymentStatusEvent() {
        // Arrange
        Long orderId = 123L;
        Long customerId = 456L;
        String paymentStatus = "PAID";
        OffsetDateTime paymentDateTime = OffsetDateTime.now();
        String eventType = "RECEIVED_PAYMENT_STATUS";

        // Act
        ReceivedPaymentStatusEvent event = ReceivedPaymentStatusEvent.builder()
                .orderId(orderId)
                .customerId(customerId)
                .paymentStatus(paymentStatus)
                .paymentDateTime(paymentDateTime)
                .eventType(eventType)
                .build();

        // Assert
        assertReceivedPaymentStatusEvent(event, orderId, customerId, paymentStatus, paymentDateTime, eventType);
    }

    @Test
    public void testSetters() {
        // Arrange
        ReceivedPaymentStatusEvent event = ReceivedPaymentStatusEvent.builder().build();
        Long orderId = 123L;
        Long customerId = 456L;
        String paymentStatus = "PAID";
        OffsetDateTime paymentDateTime = OffsetDateTime.now();
        String eventType = "RECEIVED_PAYMENT_STATUS";

        // Act
        event.setOrderId(orderId);
        event.setCustomerId(customerId);
        event.setPaymentStatus(paymentStatus);
        event.setPaymentDateTime(paymentDateTime);
        event.setEventType(eventType);

        // Assert
        assertReceivedPaymentStatusEvent(event, orderId, customerId, paymentStatus, paymentDateTime, eventType);
    }

    private void assertReceivedPaymentStatusEvent(ReceivedPaymentStatusEvent event, Long orderId, Long customerId, String paymentStatus, OffsetDateTime paymentDateTime, String eventType) {
        Assertions.assertNotNull(event);
        Assertions.assertEquals(orderId, event.getOrderId());
        Assertions.assertEquals(customerId, event.getCustomerId());
        Assertions.assertEquals(paymentStatus, event.getPaymentStatus());
        Assertions.assertEquals(paymentDateTime, event.getPaymentDateTime());
        Assertions.assertEquals(eventType, event.getEventType());
    }
}
