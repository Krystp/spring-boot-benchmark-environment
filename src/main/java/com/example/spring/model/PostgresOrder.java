package com.example.spring.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Entity
@Table(name = "orders")
@Data
@NoArgsConstructor
public class PostgresOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Setter private String product;
    @Setter private int amount;

    @ManyToOne
    @JoinColumn(name = "analiza_id")
    @JsonIgnore
    private Postgres analiza;
}
