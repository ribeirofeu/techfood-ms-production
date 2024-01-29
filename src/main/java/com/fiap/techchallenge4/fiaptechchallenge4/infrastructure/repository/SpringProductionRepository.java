package com.fiap.techchallenge4.fiaptechchallenge4.infrastructure.repository;

import com.fiap.techchallenge4.fiaptechchallenge4.domain.production.Production;
import com.fiap.techchallenge4.fiaptechchallenge4.domain.production.ProductionStatus;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface SpringProductionRepository extends MongoRepository<Production, String> {

    // Find Production by order id
    Optional<Production> findByOrderId(String orderId);
    List<Production> findAllByStatusIn(List<ProductionStatus> statuses);
}
