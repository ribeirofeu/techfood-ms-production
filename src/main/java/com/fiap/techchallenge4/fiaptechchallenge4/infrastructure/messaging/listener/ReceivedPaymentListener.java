package com.fiap.techchallenge4.fiaptechchallenge4.infrastructure.messaging.listener;

import com.fiap.techchallenge4.fiaptechchallenge4.application.interfaces.usecases.ProductionUseCases;
import com.fiap.techchallenge4.fiaptechchallenge4.infrastructure.messaging.events.ReceivedPaymentStatusEvent;
import io.awspring.cloud.sqs.annotation.SqsListener;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class ReceivedPaymentListener {
    private ProductionUseCases productionUseCases;

    @SqsListener(value = "${events.queues.received_payment}")
    public void listenReceivedPaymentEvent(ReceivedPaymentStatusEvent event) {
        log.info("Evento de recebimento de pagamento recebido. Iniciando produção de Order Id: {}", event.getOrderId());
        try {
            if (event.getPaymentStatus().equals("APPROVED")) {
                productionUseCases.addProduction(event.getOrderId().toString());
            }
            log.info("Evento processado com sucesso. Order id: {}", event.getOrderId());
        } catch (Exception exception) {
            log.error("Erro ao processar a mensagem. Motivo: {}", exception.getMessage());
            throw exception;
        }
    }

}
