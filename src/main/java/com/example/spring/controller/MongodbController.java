package com.example.spring.controller;

import com.example.spring.dto.MongodbTO;
import com.example.spring.service.MongodbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/mongodb")
public class MongodbController {

    @Autowired
    private MongodbService mongodbService;

    @GetMapping("/")
    public ResponseEntity<List<MongodbTO>> getAnaliza() {
        List<MongodbTO> analizaList = mongodbService.getAnaliza();
        return new ResponseEntity<>(analizaList, HttpStatus.OK);
    }

    @GetMapping("/{limit}")
    public ResponseEntity<List<MongodbTO>> getAnalizaLimit(@PathVariable Integer limit) {
        List<MongodbTO> analizaList = mongodbService.getAnalizaLimit(limit);
        return new ResponseEntity<>(analizaList, HttpStatus.OK);
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<MongodbTO> getAnalizaById(@PathVariable String id) {
        Optional<MongodbTO> analiza = mongodbService.getAnalizaById(id);
        return analiza.map(mongodbTO -> new ResponseEntity<>(mongodbTO, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/")
    public ResponseEntity<MongodbTO> createAnaliza(@RequestBody MongodbTO mongodbTO) {
        MongodbTO createdAnaliza = mongodbService.createAnaliza(mongodbTO);
        if (createdAnaliza != null) {
            return new ResponseEntity<>(createdAnaliza, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<MongodbTO> updateAnaliza(@RequestBody MongodbTO mongodbTO, @PathVariable String id) {
        MongodbTO updatedAnaliza = mongodbService.updateAnaliza(mongodbTO, id);
        if (updatedAnaliza != null) {
            return new ResponseEntity<>(updatedAnaliza, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAnaliza(@PathVariable String id) {
        Optional<MongodbTO> deletedAnaliza = mongodbService.deleteAnaliza(id);

        if (deletedAnaliza.isPresent()) {
            return new ResponseEntity<>(deletedAnaliza.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("No such record found", HttpStatus.NOT_FOUND);
        }
    }
}
