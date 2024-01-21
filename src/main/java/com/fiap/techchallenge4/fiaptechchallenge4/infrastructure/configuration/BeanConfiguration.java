package com.fiap.techchallenge4.fiaptechchallenge4.infrastructure.configuration;

import com.fiap.techchallenge4.fiaptechchallenge4.application.interfaces.usecases.ProductionUseCases;
import com.fiap.techchallenge4.fiaptechchallenge4.application.usecases.ProductionUseCasesImpl;
import com.fiap.techchallenge4.fiaptechchallenge4.infrastructure.repository.ProductionBdRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    ProductionUseCases productionUseCase(ProductionBdRepository repository) {
        return new ProductionUseCasesImpl(repository);
    }
}
