// OrderController.java (контроллер)
package com.example.demo.controllers;

import com.example.demo.services.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;
import com.example.demo.models.Order;
@Controller
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;
    
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }
    @GetMapping("/search")
    public String showSearchForm() {
        return "order-search"; // order-search.html
    }
    
    @GetMapping("/result")
    public String searchOrder(@RequestParam String code, Model model) {
        Optional<Order> foundOrder = orderService.findOrderByCode(code);
        model.addAttribute("searchCode", code);
        foundOrder.ifPresent(order -> model.addAttribute("order", order));
        return "order-result"; // order-result.html
    }
    @GetMapping
    public String listOrders(Model model) {
        model.addAttribute("orders", orderService.findAllOrders());
        return "orders";
    }
    
    @PostMapping("/random")
    public String createRandomOrder(@RequestParam String name, 
                                  @RequestParam String description) {
        orderService.createRandomOrder(name, description);
        return "redirect:/orders";
    }
    
    @PostMapping("/with-date")
    public String createOrderWithDate(@RequestParam String name, 
                                    @RequestParam String description) {
        orderService.createOrderWithDate(name, description);
        return "redirect:/orders";
    }
    
    @PostMapping("/with-affixes")
    public String createOrderWithAffixes(@RequestParam String name,
                                       @RequestParam String description,
                                       @RequestParam String prefix,
                                       @RequestParam String suffix) {
        orderService.createOrderWithPrefixSuffix(name, description, prefix, suffix);
        return "redirect:/orders";
    }
    
    @PostMapping("/delete/{code}")
    public String deleteOrder(@PathVariable String code) {
        orderService.deleteOrder(code);
        return "redirect:/orders";
    }
}