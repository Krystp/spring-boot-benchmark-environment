package com.example.spring.service;

import com.example.spring.model.PostgresOrder;
import com.example.spring.model.Postgres;
import com.example.spring.repository.PostgresOrderRepository;
import com.example.spring.repository.PostgresRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PostgresService {

    @Autowired
    private PostgresRepository postgresRepository;

    @Autowired
    private PostgresOrderRepository postgresOrderRepository;

    public List<Postgres> getAnaliza() {
        return this.postgresRepository.findAll();
    }

    public List<Postgres> getAnalizaLimit(Integer limit) {
        Pageable pageable = PageRequest.of(0, limit);
        return this.postgresRepository.findAll(pageable).getContent();
    }

    public Postgres getAnalizaById(Long id) {
        return this.postgresRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Result not found"));
    }

    public Postgres createAnaliza(Postgres postgres) {
        Postgres savedPostgres = this.postgresRepository.save(postgres);

        if (postgres.getOrders() != null && !postgres.getOrders().isEmpty()) {
            for (PostgresOrder order : postgres.getOrders()) {
                order.setAnaliza(savedPostgres);
                postgresOrderRepository.save(order);
            }
        }

        return savedPostgres;
    }

    public Postgres updateAnaliza(Postgres postgres, Long id) {
        Optional<Postgres> existingPostgres = this.postgresRepository.findById(id);
        if (existingPostgres.isPresent()) {
            Postgres existingPostgresObj = existingPostgres.get();

            existingPostgresObj.setFirstname(postgres.getFirstname());
            existingPostgresObj.setLastname(postgres.getLastname());
            existingPostgresObj.setAge(postgres.getAge());
            existingPostgresObj.setPhone(postgres.getPhone());

            existingPostgresObj.getOrders().clear();
            postgresOrderRepository.deleteAll(existingPostgresObj.getOrders());

            if (postgres.getOrders() != null && !postgres.getOrders().isEmpty()) {
                for (PostgresOrder order : postgres.getOrders()) {
                    order.setAnaliza(existingPostgresObj);
                    existingPostgresObj.getOrders().add(postgresOrderRepository.save(order));
                }
            }

            return this.postgresRepository.save(existingPostgresObj);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Result not found");
        }
    }


    public Postgres deleteAnaliza(Long id) {
        Postgres postgres = getAnalizaById(id);
        this.postgresRepository.delete(postgres);
        return postgres;
    }
}
