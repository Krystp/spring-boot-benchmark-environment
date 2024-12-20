package com.example.spring.controller;

import com.example.spring.model.Postgres;
import com.example.spring.service.PostgresService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/postgres")
public class PostgresController {

    @Autowired
    private PostgresService postgresService;

    @GetMapping("/")
    public ResponseEntity<List<Postgres>> getAnaliza() {
        List<Postgres> postgres = postgresService.getAnaliza();
        return ResponseEntity.ok().body(postgres);
    }

    @GetMapping("/{limit}")
    public ResponseEntity<List<Postgres>> getAnaliza(@PathVariable Integer limit) {
        List<Postgres> postgres = postgresService.getAnalizaLimit(limit);
        return ResponseEntity.ok().body(postgres);
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<Postgres> getAnalizaById(@PathVariable("id") Long id) {
        Postgres postgres = postgresService.getAnalizaById(id);
        return ResponseEntity.ok().body(postgres);
    }

    @PostMapping("/")
    public ResponseEntity<Postgres> createAnaliza(@RequestBody Postgres postgres) {
        Postgres createdAnaliza = postgresService.createAnaliza(postgres);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdAnaliza);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Postgres> updateAnaliza(@RequestBody Postgres postgres, @PathVariable("id") Long id) {
        Postgres updatedAnaliza = postgresService.updateAnaliza(postgres, id);
        return ResponseEntity.ok().body(updatedAnaliza);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Postgres> deleteAnaliza(@PathVariable("id") Long id) {
        Postgres deletedAnaliza = postgresService.deleteAnaliza(id);
        return ResponseEntity.status(HttpStatus.OK).body(deletedAnaliza);
    }
}
