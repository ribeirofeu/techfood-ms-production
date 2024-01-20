package com.fiap.techchallenge4.fiaptechchallenge4.domain.production;

import lombok.Builder;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document
@Data
@Builder
public class Production {

    @Id
    private ObjectId _id;
    private String orderId;
    private LocalDateTime receivedDate;
    private ProductionStatus status;

}
