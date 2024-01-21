package com.fiap.techchallenge4.fiaptechchallenge4.infrastructure.controller;

import com.fiap.techchallenge4.fiaptechchallenge4.application.dto.request.AddProductionDTO;
import com.fiap.techchallenge4.fiaptechchallenge4.application.dto.request.SetProductionStatusDTO;
import com.fiap.techchallenge4.fiaptechchallenge4.application.interfaces.usecases.ProductionUseCases;
import com.fiap.techchallenge4.fiaptechchallenge4.domain.production.Production;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/production")
@Tag(name = "Produção")
public class ProductionController {

    private ProductionUseCases useCases;

    @Autowired
    public ProductionController(ProductionUseCases useCases) {
        this.useCases = useCases;
    }

    @PostMapping
    @Operation(summary = "Adiciona uma nova produção")
    public Production addProduction(@RequestBody AddProductionDTO addProductionDTO) {
        return useCases.addProduction(addProductionDTO.orderId());
    }

    @PutMapping("/{orderId}")
    @Operation(summary = "Atualiza o status de uma produção")
    public Production setProductionStatus(@PathVariable String orderId, @RequestBody SetProductionStatusDTO setProductionStatusDTO) {
        return useCases.setProductionStatus(orderId, setProductionStatusDTO.status());
    }

    @GetMapping("/")
    @Operation(summary = "Lista todas as produções não finalizadas ordenadas por status e horário de recebimento")
    public List<Production> getNotCompletedProduction() {
        return useCases.getNotCompletedProduction();
    }
}
