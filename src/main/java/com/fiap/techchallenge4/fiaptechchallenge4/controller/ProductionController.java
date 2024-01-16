package com.fiap.techchallenge4.fiaptechchallenge4.controller;

import com.fiap.techchallenge4.fiaptechchallenge4.domain.Production;
import com.fiap.techchallenge4.fiaptechchallenge4.dtos.request.AddProductionDTO;
import com.fiap.techchallenge4.fiaptechchallenge4.dtos.request.SetProductionStatusDTO;
import com.fiap.techchallenge4.fiaptechchallenge4.service.ProductionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/production")
@Tag(name = "Produção")
public class ProductionController {

    private ProductionService productionService;

    @PostMapping
    @Operation(summary = "Adiciona uma nova produção")
    public Production addProduction(@RequestBody AddProductionDTO addProductionDTO) {
        return productionService.addProduction(addProductionDTO.orderId());
    }

    @PutMapping("/{orderId}")
    @Operation(summary = "Atualiza o status de uma produção")
    public Production setProductionStatus(@PathVariable String orderId, @RequestBody SetProductionStatusDTO setProductionStatusDTO) {
        return productionService.setStatus(orderId, setProductionStatusDTO.status());
    }

    @GetMapping("/")
    @Operation(summary = "Lista todas as produções não finalizadas ordenadas por status e horário de recebimento")
    public List<Production> getNotCompletedProduction() {
        return productionService.getNotCompletedProduction();
    }
}
