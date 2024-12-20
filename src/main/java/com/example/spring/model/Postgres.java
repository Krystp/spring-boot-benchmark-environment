package com.example.spring.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@SuperBuilder
@Entity
@Table(name = "analiza")
@Data
@NoArgsConstructor
public class Postgres {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Setter private String firstname;
    @Setter private String lastname;
    @Setter private int age;
    @Setter private int phone;

    @OneToMany(mappedBy = "analiza", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PostgresOrder> orders;
}
