package com.fiap.techchallenge4.fiaptechchallenge4.utils;

import com.fiap.techchallenge4.fiaptechchallenge4.application.dto.request.AddProductionDTO;

import java.security.SecureRandom;

public class ProductionHelper {
    public static AddProductionDTO buildAddProductionDTO() {
        int randomId = new SecureRandom().nextInt(100000) + 1;
        return new AddProductionDTO(String.valueOf(randomId));
    }

    public static AddProductionDTO buildAddProductionDTO(String orderId) {
        return new AddProductionDTO(orderId);
    }
}
