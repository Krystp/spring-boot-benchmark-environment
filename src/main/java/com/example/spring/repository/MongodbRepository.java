package com.example.spring.repository;


import com.example.spring.model.Mongodb;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MongodbRepository extends MongoRepository<Mongodb, String> {
}
