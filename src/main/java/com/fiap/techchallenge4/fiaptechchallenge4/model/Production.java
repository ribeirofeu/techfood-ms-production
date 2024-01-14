package com.fiap.techchallenge4.fiaptechchallenge4.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;

@Document
public class Production {

    @Id
    private String id;
    private String orderId;
    private LocalDateTime receivedDate;
    private String status;

}
