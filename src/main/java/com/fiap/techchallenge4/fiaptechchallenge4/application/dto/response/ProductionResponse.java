package com.fiap.techchallenge4.fiaptechchallenge4.application.dto.response;

import com.fiap.techchallenge4.fiaptechchallenge4.domain.production.ProductionStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductionResponse {
    private String orderId;
    private LocalDateTime receivedDate;
    private ProductionStatus status;
}
