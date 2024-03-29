package com.fiap.techchallenge4.fiaptechchallenge4.domain.production;

import com.fiap.techchallenge4.fiaptechchallenge4.domain.commom.Event;
import com.fiap.techchallenge4.fiaptechchallenge4.domain.commom.EventType;
import lombok.Builder;
import lombok.Getter;

import java.time.OffsetDateTime;

@Getter
@Builder
public class CompletedOrderEvent implements Event {
    private Long orderId;
    private OffsetDateTime datetime;
    @Override
    public EventType getEventType() {
        return EventType.COMPLETED_ORDER;
    }
}
