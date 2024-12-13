package com.sfass.bsamonitoring.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Connection;
import java.sql.DriverManager;

@RestController
public class HelloController {

    @GetMapping("/api/hello")
    public String sayHello() {
        try (Connection connection = DriverManager.getConnection(
                "jdbc:mysql://mysql:3306/testdb", "testuser", "testpass")) {
            return "Hello, World! MySQL connection successful.";
        } catch (Exception e) {
            return "Hello, World! MySQL connection failed: " + e.getMessage();
        }
    }
}
