package com.fiap.techchallenge4.fiaptechchallenge4.service;

import com.fiap.techchallenge4.fiaptechchallenge4.domain.ProductionStatus;
import com.fiap.techchallenge4.fiaptechchallenge4.domain.Production;
import com.fiap.techchallenge4.fiaptechchallenge4.repository.ProductionRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class ProductionService {

    ProductionRepository productionRepository;

    public Production addProduction(String orderId) {
        return productionRepository.insert(Production.builder()
                .orderId(orderId)
                .status(ProductionStatus.RECEIVED)
                .receivedDate(LocalDateTime.now())
                .build());
    }

    public Production setStatus(String orderId, ProductionStatus productionStatus) {
        Production foundProduction = productionRepository.findByOrderId(orderId);
        foundProduction.setStatus(productionStatus);
        productionRepository.save(foundProduction);
        return foundProduction;
    }
}
