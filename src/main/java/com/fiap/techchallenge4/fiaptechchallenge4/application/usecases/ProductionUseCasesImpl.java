package com.fiap.techchallenge4.fiaptechchallenge4.application.usecases;

import com.fiap.techchallenge4.fiaptechchallenge4.application.interfaces.gateways.ProductionRepository;
import com.fiap.techchallenge4.fiaptechchallenge4.application.interfaces.usecases.ProductionUseCases;
import com.fiap.techchallenge4.fiaptechchallenge4.domain.production.Production;
import com.fiap.techchallenge4.fiaptechchallenge4.domain.production.ProductionStatus;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ProductionUseCasesImpl implements ProductionUseCases {

    private final ProductionRepository repository;

    public ProductionUseCasesImpl(final ProductionRepository repository) {
        this.repository = repository;
    }

    @Override
    public Production addProduction(String orderId) {
        repository.findByOrderId(orderId).ifPresent(production -> {
            throw new IllegalArgumentException("Production with order id " + orderId + " already exists");
        });

        return repository.insert(Production.builder()
                .orderId(orderId)
                .status(ProductionStatus.RECEIVED)
                .receivedDate(LocalDateTime.now())
                .build());
    }

    @Override
    public Production setProductionStatus(String orderId, ProductionStatus productionStatus) {
        Production foundProduction = repository.findByOrderId(orderId)
                .orElseThrow(() -> new IllegalArgumentException("Production with order id " + orderId + " not found"));

        foundProduction.setStatus(productionStatus);
        return repository.save(foundProduction);
    }

    @Override
    public List<Production> getNotCompletedProduction() {
        return repository.findAllByStatusIn(List.of(ProductionStatus.IN_PREPARATION, ProductionStatus.READY)).stream()
                .filter(production -> production.getReceivedDate() != null)
                .sorted(Comparator.comparing((Production production) -> production.getStatus().getDisplayPriority())
                        .thenComparing(Production::getReceivedDate))
                .collect(Collectors.toList());
    }
}
