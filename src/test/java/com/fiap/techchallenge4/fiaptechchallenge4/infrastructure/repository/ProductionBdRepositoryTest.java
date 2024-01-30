package com.fiap.techchallenge4.fiaptechchallenge4.infrastructure.repository;

import com.fiap.techchallenge4.fiaptechchallenge4.domain.production.Production;
import com.fiap.techchallenge4.fiaptechchallenge4.domain.production.ProductionStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class ProductionBdRepositoryTest {

    private ProductionBdRepository productionBdRepository;

    @Mock
    private SpringProductionRepository springProductionRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        productionBdRepository = new ProductionBdRepository(springProductionRepository);
    }

    @Test
    void shouldFindByOrderId() {
        String orderId = "123";
        Production production = Production.builder()
                .orderId(orderId)
                .status(ProductionStatus.RECEIVED)
                .build();
        when(springProductionRepository.findByOrderId(orderId)).thenReturn(Optional.of(production));

        Optional<Production> foundProduction = productionBdRepository.findByOrderId(orderId);

        assertEquals(production, foundProduction.orElse(null));
    }

    @Test
    void shouldFindAllByStatusIn() {
        List<ProductionStatus> statuses = Arrays.asList(ProductionStatus.RECEIVED, ProductionStatus.IN_PREPARATION);
        Production production1 = Production.builder()
                .orderId("123")
                .status(ProductionStatus.RECEIVED)
                .build();
        Production production2 = Production.builder()
                .orderId("456")
                .status(ProductionStatus.IN_PREPARATION)
                .build();
        List<Production> expectedProductions = Arrays.asList(production1, production2);
        when(springProductionRepository.findAllByStatusIn(statuses)).thenReturn(expectedProductions);

        List<Production> foundProductions = productionBdRepository.findAllByStatusIn(statuses);

        assertEquals(expectedProductions, foundProductions);
    }

    @Test
    void shouldSaveProduction() {
        Production production = Production.builder()
                .orderId("123")
                .status(ProductionStatus.RECEIVED)
                .build();
        when(springProductionRepository.save(any(Production.class)))
                .thenReturn(production);

        Production savedProduction = productionBdRepository.save(production);

        assertEquals(production, savedProduction);
    }

    @Test
    void shouldInsertProduction() {
        Production production = Production.builder()
                .orderId("123")
                .status(ProductionStatus.RECEIVED)
                .build();
        when(springProductionRepository.insert(any(Production.class))).thenReturn(production);

        Production insertedProduction = productionBdRepository.insert(production);

        assertEquals(production, insertedProduction);
    }

}