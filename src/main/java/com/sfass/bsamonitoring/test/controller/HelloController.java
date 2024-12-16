package com.sfass.bsamonitoring.test.controller;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Connection;
import java.sql.DriverManager;

@ActiveProfiles("test")
@RestController
public class HelloController {

    @GetMapping("/api/hello")
    public String sayHello() {
        try (Connection connection = DriverManager.getConnection(
                "jdbc:mysql://mysql:3306/bsa_monitoring", "test", "a2374168")) {
            return "Hello, World! MySQL connection successful.";
        } catch (Exception e) {
            return "Hello, World! MySQL connection failed: " + e.getMessage();
        }
    }
}
