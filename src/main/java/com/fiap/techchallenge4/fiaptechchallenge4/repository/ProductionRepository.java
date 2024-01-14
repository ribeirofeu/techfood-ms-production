package com.fiap.techchallenge4.fiaptechchallenge4.repository;

import com.fiap.techchallenge4.fiaptechchallenge4.domain.Production;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductionRepository extends MongoRepository<Production, String> {

    Production findByOrderId(String orderId);
}
