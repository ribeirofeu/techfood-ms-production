package com.fiap.techchallenge4.fiaptechchallenge4.infrastructure.controller;

import com.fiap.techchallenge4.fiaptechchallenge4.application.interfaces.usecases.ProductionUseCases;
import com.fiap.techchallenge4.fiaptechchallenge4.domain.production.Production;
import com.fiap.techchallenge4.fiaptechchallenge4.domain.production.ProductionStatus;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;


class ProductionControllerTest {
    private MockMvc mockMvc;

    AutoCloseable mock;

    @Mock
    private ProductionUseCases productionUseCases;

    @BeforeEach
    void setup(){
        mock = MockitoAnnotations.openMocks(this);
        ProductionController productionController = new ProductionController(productionUseCases);
        mockMvc = MockMvcBuilders.standaloneSetup(productionController).build();
    }

    @AfterEach
    void tearDown() throws Exception{
        mock.close();
    }

    @Test
    void shouldCallCreateProductionSuccess() throws Exception {
        Mockito.when(productionUseCases.addProduction(Mockito.anyString()))
                .thenReturn(Production.builder().
                        _id(new ObjectId())
                        .receivedDate(LocalDateTime.now())
                        .status(ProductionStatus.RECEIVED)
                        .build());

        mockMvc.perform(MockMvcRequestBuilders.post("/production")
                        .content("{\"orderId\":\"1\"}").contentType("application/json"))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    void shouldUpdateProductionStatus() throws Exception {
        String orderId = "1";
        ProductionStatus newStatus = ProductionStatus.IN_PREPARATION;
        Mockito.when(productionUseCases.setProductionStatus(orderId, newStatus))
                .thenReturn(Production.builder()
                        ._id(new ObjectId())
                        .receivedDate(LocalDateTime.now())
                        .status(newStatus)
                        .build());

        mockMvc.perform(MockMvcRequestBuilders.put("/production/{orderId}", orderId)
                        .content("{\"status\":\"IN_PREPARATION\"}")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void shouldListNotCompletedProductions() throws Exception {
        Production production1 = Production.builder()
                ._id(new ObjectId())
                .receivedDate(LocalDateTime.now().minusDays(1))
                .status(ProductionStatus.RECEIVED)
                .build();

        Production production2 = Production.builder()
                ._id(new ObjectId())
                .receivedDate(LocalDateTime.now())
                .status(ProductionStatus.IN_PREPARATION)
                .build();

        List<Production> productionList = Arrays.asList(production1, production2);
        Mockito.when(productionUseCases.getNotCompletedProduction())
                .thenReturn(productionList);

        mockMvc.perform(MockMvcRequestBuilders.get("/production/"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

}