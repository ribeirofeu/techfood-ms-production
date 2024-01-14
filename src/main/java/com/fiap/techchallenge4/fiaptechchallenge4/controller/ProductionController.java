package com.fiap.techchallenge4.fiaptechchallenge4.controller;

import com.fiap.techchallenge4.fiaptechchallenge4.domain.Production;
import com.fiap.techchallenge4.fiaptechchallenge4.dtos.request.AddProductionDTO;
import com.fiap.techchallenge4.fiaptechchallenge4.dtos.request.SetProductionStatusDTO;
import com.fiap.techchallenge4.fiaptechchallenge4.service.ProductionService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/production")
public class ProductionController {

    private ProductionService productionService;

    @PostMapping
    public Production addProduction(@RequestBody AddProductionDTO addProductionDTO) {
        return productionService.addProduction(addProductionDTO.orderId());
    }

    @PutMapping("/{orderId}")
    public Production setProductionStatus(@PathVariable String orderId, @RequestBody SetProductionStatusDTO setProductionStatusDTO) {
        return productionService.setStatus(orderId, setProductionStatusDTO.status());
    }
}
