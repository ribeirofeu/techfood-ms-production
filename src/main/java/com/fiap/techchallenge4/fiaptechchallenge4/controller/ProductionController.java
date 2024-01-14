package com.fiap.techchallenge4.fiaptechchallenge4.controller;

import com.fiap.techchallenge4.fiaptechchallenge4.domain.Production;
import com.fiap.techchallenge4.fiaptechchallenge4.dtos.request.ProductionDTO;
import com.fiap.techchallenge4.fiaptechchallenge4.service.ProductionService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/production")
public class ProductionController {

    private ProductionService productionService;

    @PostMapping
        public Production addProduction(@RequestBody ProductionDTO productionDTO) {
        return productionService.addProduction(productionDTO.orderId());
    }
}
