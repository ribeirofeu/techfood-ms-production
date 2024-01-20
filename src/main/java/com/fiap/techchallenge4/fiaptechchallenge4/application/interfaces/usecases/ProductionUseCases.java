package com.fiap.techchallenge4.fiaptechchallenge4.application.interfaces.usecases;

import com.fiap.techchallenge4.fiaptechchallenge4.domain.production.Production;
import com.fiap.techchallenge4.fiaptechchallenge4.domain.production.ProductionStatus;

import java.util.List;

public interface ProductionUseCases {
    Production addProduction(String orderId);

    Production setProductionStatus(String orderId, ProductionStatus productionStatus);

    List<Production> getNotCompletedProduction();
}
