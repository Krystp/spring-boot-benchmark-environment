package com.example.spring.repository;

import com.example.spring.model.PostgresOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostgresOrderRepository extends JpaRepository<PostgresOrder, Long> {
}
