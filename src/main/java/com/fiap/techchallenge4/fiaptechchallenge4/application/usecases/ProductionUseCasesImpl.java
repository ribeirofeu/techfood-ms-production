package com.fiap.techchallenge4.fiaptechchallenge4.application.usecases;

import com.fiap.techchallenge4.fiaptechchallenge4.application.interfaces.gateways.ProductionMessageSender;
import com.fiap.techchallenge4.fiaptechchallenge4.application.interfaces.gateways.ProductionRepository;
import com.fiap.techchallenge4.fiaptechchallenge4.application.interfaces.usecases.ProductionUseCases;
import com.fiap.techchallenge4.fiaptechchallenge4.domain.production.CompletedOrderEvent;
import com.fiap.techchallenge4.fiaptechchallenge4.domain.production.Production;
import com.fiap.techchallenge4.fiaptechchallenge4.domain.production.ProductionStatus;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.Comparator;
import java.util.List;

public class ProductionUseCasesImpl implements ProductionUseCases {

    private final ProductionRepository repository;

    private final ProductionMessageSender productionMessageSender;

    public ProductionUseCasesImpl(final ProductionRepository repository, final ProductionMessageSender productionMessageSender) {
        this.repository = repository;
        this.productionMessageSender = productionMessageSender;
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
        Production production = repository.findByOrderId(orderId)
                .orElseThrow(() -> new IllegalArgumentException("Production with order id " + orderId + " not found"));

        production.setStatus(productionStatus);
        var savedProduction = repository.save(production);

        if (productionStatus.equals(ProductionStatus.COMPLETED)) {
            productionMessageSender.publish(CompletedOrderEvent.builder()
                    .orderId(Long.parseLong(savedProduction.getOrderId()))
                    .datetime(OffsetDateTime.now())
                    .build());
        }

        return savedProduction;
    }



    @Override
    public List<Production> getNotCompletedProduction() {
        return repository.findAllByStatusIn(List.of(ProductionStatus.IN_PREPARATION, ProductionStatus.READY)).stream()
                .filter(production -> production.getReceivedDate() != null)
                .sorted(Comparator.comparing((Production production) -> production.getStatus().getDisplayPriority())
                        .thenComparing(Production::getReceivedDate))
                .toList();
    }
}
