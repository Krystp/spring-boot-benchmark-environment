package com.example.spring.repository;

import com.example.spring.model.MongodbOrder;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MongodbOrderRepository extends MongoRepository<MongodbOrder, String> {
    void deleteManyByAnaliza_Id(String analizaId);
    List<MongodbOrder> findByAnaliza_Id(String analizaId);
}
