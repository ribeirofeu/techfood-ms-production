package com.fiap.techchallenge4.fiaptechchallenge4.application.interfaces.gateways;

import com.fiap.techchallenge4.fiaptechchallenge4.domain.production.Production;
import com.fiap.techchallenge4.fiaptechchallenge4.domain.production.ProductionStatus;

import java.util.List;

public interface ProductionRepository {

    Production save(Production production);
    Production insert(Production production);
    Production findByOrderId(String orderId);
    List<Production> findAllByStatusIn(List<ProductionStatus> statuses);
}
