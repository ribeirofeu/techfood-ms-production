package com.fiap.techchallenge4.fiaptechchallenge4.infrastructure.repository;

import com.fiap.techchallenge4.fiaptechchallenge4.domain.production.Production;
import com.fiap.techchallenge4.fiaptechchallenge4.domain.production.ProductionStatus;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface SpringProductionRepository extends MongoRepository<Production, String> {

    // Find Production by order id
    Production findByOrderId(String orderId);
    List<Production> findAllByStatusIn(List<ProductionStatus> statuses);
}