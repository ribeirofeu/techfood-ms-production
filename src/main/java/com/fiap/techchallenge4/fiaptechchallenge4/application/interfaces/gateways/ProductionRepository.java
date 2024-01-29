package com.fiap.techchallenge4.fiaptechchallenge4.application.interfaces.gateways;

import com.fiap.techchallenge4.fiaptechchallenge4.domain.production.Production;
import com.fiap.techchallenge4.fiaptechchallenge4.domain.production.ProductionStatus;

import java.util.List;
import java.util.Optional;

public interface ProductionRepository {

    Production save(Production production);
    Production insert(Production production);
    Optional<Production> findByOrderId(String orderId);
    List<Production> findAllByStatusIn(List<ProductionStatus> statuses);
}
