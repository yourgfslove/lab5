// OrderCodeGenerator.java (сервис генерации кодов)
package com.example.demo.services;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Random;

@Service
public class OrderCodeGenerator {
    private final Random random = new Random();
    
    public String generateRandomCode() {
        return String.format("%06d", random.nextInt(1000000));
    }
    
    public String generateCodeWithDate() {
        String date = LocalDate.now().format(java.time.format.DateTimeFormatter.ofPattern("ddMMyyyy"));
        return generateRandomCode() + date;
    }
    
    public String generateCodeWithPrefixSuffix(String prefix, String suffix) {
        return prefix + generateRandomCode() + suffix;
    }
}