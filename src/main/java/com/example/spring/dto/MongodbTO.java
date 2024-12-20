package com.example.spring.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class MongodbTO {
    private String id;
    private String firstName;
    private String lastName;
    private int age;
    private int phone;
    private List<MongodbOrderTO> orders;
}
