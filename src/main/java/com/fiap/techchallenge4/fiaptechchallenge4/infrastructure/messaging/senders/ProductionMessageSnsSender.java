package com.fiap.techchallenge4.fiaptechchallenge4.infrastructure.messaging.senders;

import com.fiap.techchallenge4.fiaptechchallenge4.application.interfaces.gateways.ProductionMessageSender;
import com.fiap.techchallenge4.fiaptechchallenge4.domain.commom.Event;
import io.awspring.cloud.sns.core.SnsTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Slf4j
@Component
public class ProductionMessageSnsSender implements ProductionMessageSender {
    @Value("${events.output}")
    private String topic;

    private final SnsTemplate snsTemplate;

    public ProductionMessageSnsSender(SnsTemplate snsTemplate) {
        this.snsTemplate = snsTemplate;
    }

    @Override
    public <T extends Event> void publish(T event) {
        snsTemplate.convertAndSend(topic, event);
        log.info("Message sent to the topic");
    }
}
