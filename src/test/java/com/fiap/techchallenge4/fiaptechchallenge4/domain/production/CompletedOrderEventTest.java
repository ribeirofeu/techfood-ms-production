package com.fiap.techchallenge4.fiaptechchallenge4.domain.production;

import com.fiap.techchallenge4.fiaptechchallenge4.domain.commom.EventType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.OffsetDateTime;

public class CompletedOrderEventTest {

    @Test
    public void testCompletedOrderEventCreation() {
        // Arrange
        Long orderId = 123L;
        OffsetDateTime datetime = OffsetDateTime.now();

        // Act
        CompletedOrderEvent event = CompletedOrderEvent.builder()
                .orderId(orderId)
                .datetime(datetime)
                .build();

        // Assert
        Assertions.assertNotNull(event);
        Assertions.assertEquals(orderId, event.getOrderId());
        Assertions.assertEquals(datetime, event.getDatetime());
        Assertions.assertEquals(EventType.COMPLETED_ORDER, event.getEventType());
    }
}
