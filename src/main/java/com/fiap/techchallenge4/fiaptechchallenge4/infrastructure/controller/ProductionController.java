package com.fiap.techchallenge4.fiaptechchallenge4.infrastructure.controller;

import com.fiap.techchallenge4.fiaptechchallenge4.application.dto.request.AddProductionDTO;
import com.fiap.techchallenge4.fiaptechchallenge4.application.dto.request.SetProductionStatusDTO;
import com.fiap.techchallenge4.fiaptechchallenge4.application.dto.response.ProductionResponse;
import com.fiap.techchallenge4.fiaptechchallenge4.application.interfaces.usecases.ProductionUseCases;
import com.fiap.techchallenge4.fiaptechchallenge4.domain.production.Production;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<ProductionResponse> addProduction(@RequestBody AddProductionDTO addProductionDTO) {
        try {
            Production createdProduction = useCases.addProduction(addProductionDTO.orderId());
            return ResponseEntity.status(HttpStatus.CREATED).body(ProductionResponse.builder()
                    .status(createdProduction.getStatus())
                    .orderId(createdProduction.getOrderId())
                    .receivedDate(createdProduction.getReceivedDate())
                    .build());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{orderId}")
    @Operation(summary = "Atualiza o status de uma produção")
    public ProductionResponse setProductionStatus(@PathVariable String orderId, @RequestBody SetProductionStatusDTO setProductionStatusDTO) {
        Production updatedProduction = useCases.setProductionStatus(orderId, setProductionStatusDTO.status());
        return ProductionResponse.builder()
                .status(updatedProduction.getStatus())
                .orderId(updatedProduction.getOrderId())
                .receivedDate(updatedProduction.getReceivedDate())
                .build();
    }

    @GetMapping("/")
    @Operation(summary = "Lista todas as produções não finalizadas ordenadas por status e horário de recebimento")
    public List<Production> getNotCompletedProduction() {
        return useCases.getNotCompletedProduction();
    }
}
