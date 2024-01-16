package com.fiap.techchallenge4.fiaptechchallenge4.repository;

import com.fiap.techchallenge4.fiaptechchallenge4.domain.Production;
import com.fiap.techchallenge4.fiaptechchallenge4.domain.ProductionStatus;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ProductionRepository extends MongoRepository<Production, String> {

    // Find Production by order id
    Production findByOrderId(String orderId);
    List<Production> findAllByStatusIn(List<ProductionStatus> statuses);
}
