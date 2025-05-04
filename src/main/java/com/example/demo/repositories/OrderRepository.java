// OrderRepository.java (репозиторий)
package com.example.demo.repositories;

import com.example.demo.models.Order;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository {
    Optional<Order> findByCode(String code);
    Order add(Order order);
    List<Order> findAll();
    boolean deleteByCode(String code);
}