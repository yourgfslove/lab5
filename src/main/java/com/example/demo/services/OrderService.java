// OrderService.java (основной сервис)
package com.example.demo.services;

import com.example.demo.models.Order;
import com.example.demo.repositories.OrderRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderCodeGenerator codeGenerator;
    
    public OrderService(OrderRepository orderRepository, OrderCodeGenerator codeGenerator) {
        this.orderRepository = orderRepository;
        this.codeGenerator = codeGenerator;
    }
    
    public Order createRandomOrder(String name, String description) {
        String code = codeGenerator.generateRandomCode();
        Order order = new Order(code, name, description);
        return orderRepository.add(order);
    }
    
    public Order createOrderWithDate(String name, String description) {
        String code = codeGenerator.generateCodeWithDate();
        Order order = new Order(code, name, description);
        return orderRepository.add(order);
    }
    
    public Order createOrderWithPrefixSuffix(String name, String description, String prefix, String suffix) {
        String code = codeGenerator.generateCodeWithPrefixSuffix(prefix, suffix);
        Order order = new Order(code, name, description);
        return orderRepository.add(order);
    }
    
    public Optional<Order> findOrderByCode(String code) {
        return orderRepository.findByCode(code);
    }
    
    public List<Order> findAllOrders() {
        return orderRepository.findAll();
    }
    
    public boolean deleteOrder(String code) {
        return orderRepository.deleteByCode(code);
    }
}