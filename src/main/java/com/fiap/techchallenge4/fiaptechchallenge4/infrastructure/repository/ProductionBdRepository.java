package com.fiap.techchallenge4.fiaptechchallenge4.infrastructure.repository;

import com.fiap.techchallenge4.fiaptechchallenge4.application.interfaces.gateways.ProductionRepository;
import com.fiap.techchallenge4.fiaptechchallenge4.domain.production.Production;
import com.fiap.techchallenge4.fiaptechchallenge4.domain.production.ProductionStatus;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductionBdRepository implements ProductionRepository {

    SpringProductionRepository repository;

    public ProductionBdRepository(SpringProductionRepository repository) {
        this.repository = repository;
    }

    @Override
    public Production findByOrderId(String orderId) {
        return repository.findByOrderId(orderId);
    }

    @Override
    public List<Production> findAllByStatusIn(List<ProductionStatus> statuses) {
        return repository.findAllByStatusIn(statuses);
    }

    @Override
    public Production save(Production production) {
        return repository.save(production);
    }

    @Override
    public Production insert(Production production) {
        return repository.insert(production);
    }
}
