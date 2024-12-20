package com.example.spring.repository;

import com.example.spring.model.Postgres;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostgresRepository extends JpaRepository<Postgres, Long> {
}
