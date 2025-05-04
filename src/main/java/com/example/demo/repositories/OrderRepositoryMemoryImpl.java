package com.example.demo.repositories;

import com.example.demo.models.Order;
import org.springframework.stereotype.Repository;
import org.springframework.context.annotation.Profile;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
@Profile("memory") // Активируется с профилем "memory"
public class OrderRepositoryMemoryImpl implements OrderRepository {
    private final List<Order> orders = new ArrayList<>();

    @Override
    public Optional<Order> findByCode(String code) {
        return orders.stream()
                .filter(order -> order.getCode().equals(code))
                .findFirst();
    }

    @Override
    public Order add(Order order) {
        orders.add(order);
        return order;
    }

    @Override
    public List<Order> findAll() {
        return new ArrayList<>(orders);
    }

    @Override
    public boolean deleteByCode(String code) {
        return orders.removeIf(order -> order.getCode().equals(code));
    }
}