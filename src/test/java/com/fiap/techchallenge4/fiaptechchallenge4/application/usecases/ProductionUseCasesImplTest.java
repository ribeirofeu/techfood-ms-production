package com.fiap.techchallenge4.fiaptechchallenge4.application.usecases;

import com.fiap.techchallenge4.fiaptechchallenge4.application.interfaces.gateways.ProductionRepository;
import com.fiap.techchallenge4.fiaptechchallenge4.domain.production.Production;
import com.fiap.techchallenge4.fiaptechchallenge4.domain.production.ProductionStatus;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


public class ProductionUseCasesImplTest {

    private ProductionUseCasesImpl productionUseCases;

    @Mock
    private ProductionRepository repository;

    AutoCloseable openMocks;

    @BeforeEach
    public void setUp() {
        openMocks = MockitoAnnotations.openMocks(this);
        productionUseCases = new ProductionUseCasesImpl(repository);
    }

    @AfterEach
    void tearDown() throws Exception {
        openMocks.close();
    }

    @Nested
    class AddProduction {

        @Test
        void shouldAddProductionWhenProductionWithSameOrderIdDoesNotExist() {
            // Given
            String orderId = "123";
            when(repository.findByOrderId(orderId)).thenReturn(Optional.empty());
            when(repository.insert(any(Production.class))).thenReturn(Production.builder()
                    .orderId(orderId)
                    .status(ProductionStatus.RECEIVED)
                    .receivedDate(LocalDateTime.now())
                    .build());

            // When
            Production production = productionUseCases.addProduction(orderId);

            // Then
            Assertions.assertNotNull(production);
            Assertions.assertEquals(orderId, production.getOrderId());
            Assertions.assertEquals(ProductionStatus.RECEIVED, production.getStatus());
            Assertions.assertNotNull(production.getReceivedDate());
        }

        @Test
        void shouldThrowExceptionWhenProductionWithSameOrderIdAlreadyExists() {
            // Given
            String orderId = "123";
            when(repository.findByOrderId(orderId)).thenReturn(Optional.of(Production.builder().build()));

            // When / Then
            Assertions.assertThrows(IllegalArgumentException.class, () -> {
                productionUseCases.addProduction(orderId);
            });
        }
    }

    @Nested
    class SetProductionStatus {

        @Test
        void shouldSetProductionStatus() {
            // Given
            String orderId = "123";
            Production production = Production.builder()
                    .orderId(orderId)
                    .status(ProductionStatus.RECEIVED)
                    .receivedDate(LocalDateTime.now())
                    .build();
            when(repository.findByOrderId(orderId)).thenReturn(Optional.of(production));
            when(repository.save(any(Production.class))).thenReturn(production);

            // When
            Production updatedProduction = productionUseCases.setProductionStatus(orderId, ProductionStatus.IN_PREPARATION);

            // Then
            Assertions.assertNotNull(updatedProduction);
            Assertions.assertEquals(orderId, updatedProduction.getOrderId());
            Assertions.assertEquals(ProductionStatus.IN_PREPARATION, updatedProduction.getStatus());
            Assertions.assertNotNull(updatedProduction.getReceivedDate());
        }

        @Test
        void shouldThrowExceptionWhenProductionDoesNotExist() {
            // Given
            String orderId = "123";
            when(repository.findByOrderId(orderId)).thenReturn(Optional.empty());

            // When / Then
            Assertions.assertThrows(IllegalArgumentException.class, () -> {
                productionUseCases.setProductionStatus(orderId, ProductionStatus.IN_PREPARATION);
            });
        }

        @Test
        public void testGetNotCompletedProduction_Success() {
            // Given
            List<Production> mockProductions = Arrays.asList(
                    Production.builder()._id(new ObjectId())
                            .status(ProductionStatus.IN_PREPARATION)
                            .receivedDate(LocalDateTime.now().minusHours(1))
                            .build(),
                    Production.builder()._id(new ObjectId())
                            .status(ProductionStatus.READY)
                            .receivedDate(LocalDateTime.now().minusHours(2))
                            .build()
            );

            when(repository.findAllByStatusIn(List.of(ProductionStatus.IN_PREPARATION, ProductionStatus.READY)))
                    .thenReturn(mockProductions);

            // When
            List<Production> result = productionUseCases.getNotCompletedProduction();

            // Then
            Assertions.assertNotNull(result);
            Assertions.assertEquals(2, result.size());
            Assertions.assertEquals(ProductionStatus.READY, result.get(0).getStatus());
            Assertions.assertEquals(ProductionStatus.IN_PREPARATION, result.get(1).getStatus());
            verify(repository, times(1)).findAllByStatusIn(List.of(ProductionStatus.IN_PREPARATION, ProductionStatus.READY));
        }
    }
}
