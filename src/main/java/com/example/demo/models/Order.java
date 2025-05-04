// Order.java (модель)
package com.example.demo.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Order {
    private String code;
    private String name;
    private String description;
}