package com.fiap.techchallenge4.fiaptechchallenge4.application.usecases;

import com.fiap.techchallenge4.fiaptechchallenge4.application.dto.request.AddProductionDTO;
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
        return repository.insert(Production.builder()
                .orderId(orderId)
                .status(ProductionStatus.RECEIVED)
                .receivedDate(LocalDateTime.now())
                .build());
    }

    @Override
    public Production setProductionStatus(String orderId, ProductionStatus productionStatus) {
        Production foundProduction = repository.findByOrderId(orderId);
        foundProduction.setStatus(productionStatus);
        repository.save(foundProduction);
        return foundProduction;
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
