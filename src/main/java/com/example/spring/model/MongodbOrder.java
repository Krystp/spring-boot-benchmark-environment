package com.example.spring.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DBRef;

@Document(value = "orders")
@Data
@Builder
public class MongodbOrder {
    @Id
    private String id;
    private String product;
    private int amount;

    @DBRef
    private Mongodb analiza;
}
