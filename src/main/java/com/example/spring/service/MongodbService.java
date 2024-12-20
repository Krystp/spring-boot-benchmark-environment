package com.example.spring.service;

import com.example.spring.dto.MongodbOrderTO;
import com.example.spring.dto.MongodbTO;
import com.example.spring.model.Mongodb;
import com.example.spring.model.MongodbOrder;
import com.example.spring.repository.MongodbOrderRepository;
import com.example.spring.repository.MongodbRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MongodbService {

    @Autowired
    private MongodbRepository mongodbRepository;

    @Autowired
    private MongodbOrderRepository mongodbOrderRepository;

    public List<MongodbTO> getAnaliza() {
        List<MongodbTO> analizaTOList = new ArrayList<>();
        try {
            List<Mongodb> analizaList = mongodbRepository.findAll();
            for (Mongodb analiza : analizaList) {
                List<MongodbOrder> orders = mongodbOrderRepository.findByAnaliza_Id(analiza.getId());
                List<MongodbOrderTO> orderTOs = orders.stream().map(order ->
                        MongodbOrderTO.builder()
                                .id(order.getId())
                                .product(order.getProduct())
                                .amount(order.getAmount())
                                .analizaId(order.getAnaliza().getId())
                                .build()).collect(Collectors.toList());
                MongodbTO mongodbTO = MongodbTO.builder()
                        .id(analiza.getId())
                        .firstName(analiza.getFirstName())
                        .lastName(analiza.getLastName())
                        .age(analiza.getAge())
                        .phone(analiza.getPhone())
                        .orders(orderTOs)
                        .build();
                analizaTOList.add(mongodbTO);
            }
        } catch (Exception e) {
        }
        return analizaTOList;
    }

    public List<MongodbTO> getAnalizaLimit(Integer limit) {
        List<MongodbTO> analizaTOList = new ArrayList<>();
        try {
            Pageable pageable = PageRequest.of(0, limit);
            List<Mongodb> analizaList = mongodbRepository.findAll(pageable).getContent();
            for (Mongodb analiza : analizaList) {
                List<MongodbOrder> orders = mongodbOrderRepository.findByAnaliza_Id(analiza.getId());
                List<MongodbOrderTO> orderTOs = orders.stream().map(order ->
                        MongodbOrderTO.builder()
                                .id(order.getId())
                                .product(order.getProduct())
                                .amount(order.getAmount())
                                .analizaId(order.getAnaliza().getId())
                                .build()).collect(Collectors.toList());
                MongodbTO mongodbTO = MongodbTO.builder()
                        .id(analiza.getId())
                        .firstName(analiza.getFirstName())
                        .lastName(analiza.getLastName())
                        .age(analiza.getAge())
                        .phone(analiza.getPhone())
                        .orders(orderTOs)
                        .build();
                analizaTOList.add(mongodbTO);
            }
        } catch (Exception e) {
        }
        return analizaTOList;
    }

    public Optional<MongodbTO> getAnalizaById(String id) {
        Optional<MongodbTO> analizaTO = Optional.empty();
        try {
            Optional<Mongodb> analiza = mongodbRepository.findById(id);
            if (analiza.isPresent()) {
                List<MongodbOrder> orders = mongodbOrderRepository.findByAnaliza_Id(analiza.get().getId());
                List<MongodbOrderTO> orderTOs = orders.stream().map(order ->
                        MongodbOrderTO.builder()
                                .id(order.getId())
                                .product(order.getProduct())
                                .amount(order.getAmount())
                                .analizaId(order.getAnaliza().getId())
                                .build()).collect(Collectors.toList());
                analizaTO = Optional.of(MongodbTO.builder()
                        .id(analiza.get().getId())
                        .firstName(analiza.get().getFirstName())
                        .lastName(analiza.get().getLastName())
                        .age(analiza.get().getAge())
                        .phone(analiza.get().getPhone())
                        .orders(orderTOs)
                        .build());
            }
        } catch (Exception e) {
        }
        return analizaTO;
    }

    public MongodbTO createAnaliza(MongodbTO mongodbTO) {
        try {
            Mongodb analiza = Mongodb.builder()
                    .firstName(mongodbTO.getFirstName())
                    .lastName(mongodbTO.getLastName())
                    .age(mongodbTO.getAge())
                    .phone(mongodbTO.getPhone())
                    .build();
            Mongodb savedAnaliza = mongodbRepository.save(analiza);

            List<MongodbOrderTO> orderTOs = new ArrayList<>();
            if (mongodbTO.getOrders() != null && !mongodbTO.getOrders().isEmpty()) {
                for (MongodbOrderTO orderTO : mongodbTO.getOrders()) {
                    MongodbOrder order = MongodbOrder.builder()
                            .product(orderTO.getProduct())
                            .amount(orderTO.getAmount())
                            .analiza(savedAnaliza)
                            .build();
                    MongodbOrder savedOrder = mongodbOrderRepository.save(order);
                    orderTOs.add(MongodbOrderTO.builder()
                            .id(savedOrder.getId())
                            .product(savedOrder.getProduct())
                            .amount(savedOrder.getAmount())
                            .analizaId(savedOrder.getAnaliza().getId())
                            .build());
                }
            }

            return MongodbTO.builder()
                    .id(savedAnaliza.getId())
                    .firstName(savedAnaliza.getFirstName())
                    .lastName(savedAnaliza.getLastName())
                    .age(savedAnaliza.getAge())
                    .phone(savedAnaliza.getPhone())
                    .orders(orderTOs)
                    .build();
        } catch (Exception e) {
            return null;
        }
    }

    public MongodbTO updateAnaliza(MongodbTO mongodbTO, String id) {
        try {
            Mongodb analiza = Mongodb.builder()
                    .id(id)
                    .firstName(mongodbTO.getFirstName())
                    .lastName(mongodbTO.getLastName())
                    .age(mongodbTO.getAge())
                    .phone(mongodbTO.getPhone())
                    .build();
            Mongodb updatedAnaliza = mongodbRepository.save(analiza);

            mongodbOrderRepository.deleteManyByAnaliza_Id(id);

            List<MongodbOrderTO> orderTOs = new ArrayList<>();
            if (mongodbTO.getOrders() != null && !mongodbTO.getOrders().isEmpty()) {
                for (MongodbOrderTO orderTO : mongodbTO.getOrders()) {
                    MongodbOrder order = MongodbOrder.builder()
                            .product(orderTO.getProduct())
                            .amount(orderTO.getAmount())
                            .analiza(updatedAnaliza)
                            .build();
                    MongodbOrder savedOrder = mongodbOrderRepository.save(order);
                    orderTOs.add(MongodbOrderTO.builder()
                            .id(savedOrder.getId())
                            .product(savedOrder.getProduct())
                            .amount(savedOrder.getAmount())
                            .analizaId(savedOrder.getAnaliza().getId())
                            .build());
                }
            }

            return MongodbTO.builder()
                    .id(updatedAnaliza.getId())
                    .firstName(updatedAnaliza.getFirstName())
                    .lastName(updatedAnaliza.getLastName())
                    .age(updatedAnaliza.getAge())
                    .phone(updatedAnaliza.getPhone())
                    .orders(orderTOs)
                    .build();
        } catch (Exception e) {
            return null;
        }
    }

    public Optional<MongodbTO> deleteAnaliza(String id) {
        try {
            Optional<Mongodb> analiza = mongodbRepository.findById(id);
            if (analiza.isPresent()) {
                List<MongodbOrder> orders = mongodbOrderRepository.findByAnaliza_Id(id);
                mongodbOrderRepository.deleteManyByAnaliza_Id(id);
                mongodbRepository.deleteById(id);

                List<MongodbOrderTO> orderTOs = orders.stream().map(order ->
                        MongodbOrderTO.builder()
                                .id(order.getId())
                                .product(order.getProduct())
                                .amount(order.getAmount())
                                .analizaId(order.getAnaliza().getId())
                                .build()).collect(Collectors.toList());

                MongodbTO deletedAnaliza = MongodbTO.builder()
                        .id(analiza.get().getId())
                        .firstName(analiza.get().getFirstName())
                        .lastName(analiza.get().getLastName())
                        .age(analiza.get().getAge())
                        .phone(analiza.get().getPhone())
                        .orders(orderTOs)
                        .build();

                return Optional.of(deletedAnaliza);
            }
        } catch (Exception e) {
        }
        return Optional.empty();
    }
}
