package com.fiap.techchallenge4.fiaptechchallenge4.infrastructure.configuration;

import com.fiap.techchallenge4.fiaptechchallenge4.application.interfaces.gateways.ProductionMessageSender;
import com.fiap.techchallenge4.fiaptechchallenge4.application.interfaces.usecases.ProductionUseCases;
import com.fiap.techchallenge4.fiaptechchallenge4.application.usecases.ProductionUseCasesImpl;
import com.fiap.techchallenge4.fiaptechchallenge4.infrastructure.messaging.senders.ProductionMessageSnsSender;
import com.fiap.techchallenge4.fiaptechchallenge4.infrastructure.repository.ProductionBdRepository;
import io.awspring.cloud.sns.core.SnsTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    ProductionUseCases productionUseCase(ProductionBdRepository repository, ProductionMessageSender productionMessageSender) {
        return new ProductionUseCasesImpl(repository, productionMessageSender);
    }

    @Bean
    ProductionMessageSender productionMessageSender(SnsTemplate snsTemplate) {
        return new ProductionMessageSnsSender(snsTemplate);
    }
}
