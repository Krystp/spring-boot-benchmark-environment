package com.example.spring.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MongodbOrderTO {
    private String id;
    private String product;
    private int amount;
    private String analizaId;
}
