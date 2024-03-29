package com.fiap.techchallenge4.fiaptechchallenge4.domain.production;

import org.bson.types.ObjectId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

public class ProductionTest {
    @Test
    public void testProductionCreation() {
        // Arrange
        ObjectId id = new ObjectId();
        String orderId = "123";
        Long customerId = 456L;
        LocalDateTime receivedDate = LocalDateTime.now();
        ProductionStatus status = ProductionStatus.RECEIVED;

        // Act
        Production production = new Production(id, orderId, customerId, receivedDate, status);

        // Assert
        Assertions.assertNotNull(production);
        Assertions.assertEquals(id, production.get_id());
        Assertions.assertEquals(orderId, production.getOrderId());
        Assertions.assertEquals(customerId, production.getCustomerId());
        Assertions.assertEquals(receivedDate, production.getReceivedDate());
        Assertions.assertEquals(status, production.getStatus());
    }

    @Test
    public void testProductionUpdate() {
        // Arrange
        ObjectId id = new ObjectId();
        String orderId = "123";
        Long customerId = 456L;
        LocalDateTime receivedDate = LocalDateTime.now();
        ProductionStatus status = ProductionStatus.IN_PREPARATION;

        Production production = new Production(id, orderId, customerId, receivedDate, status);

        // Act
        LocalDateTime updatedReceivedDate = LocalDateTime.now().minusDays(1);
        production.setReceivedDate(updatedReceivedDate);

        // Assert
        Assertions.assertEquals(updatedReceivedDate, production.getReceivedDate());
    }

    @Test
    public void testEqualsAndHashCode() {
        // Arrange
        ObjectId id = new ObjectId();
        String orderId = "123";
        Long customerId = 456L;
        LocalDateTime receivedDate = LocalDateTime.now();
        ProductionStatus status = ProductionStatus.IN_PREPARATION;

        Production production1 = new Production(id, orderId, customerId, receivedDate, status);
        Production production2 = new Production(id, orderId, customerId, receivedDate, status);

        // Act
        boolean areEqual = production1.equals(production2);

        // Assert
        Assertions.assertTrue(areEqual);
        Assertions.assertEquals(production1.hashCode(), production2.hashCode());
    }

    @Test
    public void testSetters() {
        // Arrange
        ObjectId id = new ObjectId();
        String orderId = "123";
        String newOrderId = "321";
        Long customerId = 456L;
        Long newCustomerId = 678L;
        LocalDateTime receivedDate = LocalDateTime.now();
        ProductionStatus status = ProductionStatus.IN_PREPARATION;

        Production production = new Production(id, orderId, customerId, receivedDate, status);

        // Act
        production.set_id(id);
        production.setOrderId(newOrderId);
        production.setCustomerId(newCustomerId);
        production.setReceivedDate(receivedDate);
        production.setStatus(status);

        // Assert
        Assertions.assertEquals(id, production.get_id());
        Assertions.assertEquals(newOrderId, production.getOrderId());
        Assertions.assertEquals(newCustomerId, production.getCustomerId());
        Assertions.assertEquals(receivedDate, production.getReceivedDate());
        Assertions.assertEquals(status, production.getStatus());
    }

    @Test
    public void testToString() {
        // Arrange
        ObjectId id = new ObjectId("61747c871fdeeb64d43be65b");
        String orderId = "123";
        Long customerId = 456L;
        LocalDateTime receivedDate = LocalDateTime.of(2022, 3, 19, 12, 30);
        ProductionStatus status = ProductionStatus.IN_PREPARATION;

        Production production = new Production(id, orderId, customerId, receivedDate, status);

        // Act
        String productionString = production.toString();

        // Assert
        String expectedString = "Production(_id=61747c871fdeeb64d43be65b, orderId=123, customerId=456, receivedDate=2022-03-19T12:30, status=IN_PREPARATION)";
        Assertions.assertEquals(expectedString, productionString);
    }
}
