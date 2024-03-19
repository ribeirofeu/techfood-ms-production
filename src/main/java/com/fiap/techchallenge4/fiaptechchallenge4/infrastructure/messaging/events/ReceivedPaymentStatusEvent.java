package com.fiap.techchallenge4.fiaptechchallenge4.infrastructure.messaging.events;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter
@Setter
@Builder
public class ReceivedPaymentStatusEvent {
    private Long orderId;
    private Long customerId;
    private String paymentStatus;
    private OffsetDateTime paymentDateTime;
    private String eventType;
}
