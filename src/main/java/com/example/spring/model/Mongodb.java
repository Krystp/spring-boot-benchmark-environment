package com.example.spring.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.List;

@Document(value = "analiza")
@Data
@Builder
public class Mongodb {
    @Id
    private String id;
    private String firstName;
    private String lastName;
    private int age;
    private int phone;

    @DBRef
    private List<MongodbOrder> orders;
}
