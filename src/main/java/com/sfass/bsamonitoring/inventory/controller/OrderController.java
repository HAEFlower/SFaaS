package com.sfass.bsamonitoring.inventory.controller;

import com.sfass.bsamonitoring.inventory.dto.request.OrderRequestDTO;
import com.sfass.bsamonitoring.inventory.dto.response.OrderResponseDTO;
import com.sfass.bsamonitoring.inventory.service.OrderService;
import com.sfass.bsamonitoring.global.GlobalResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/inventory")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/orders")
    public ResponseEntity<GlobalResponse> createOrder(@Valid @RequestBody OrderRequestDTO request) {
        OrderResponseDTO response = orderService.createOrder(request);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(GlobalResponse.ok(response));
    }
}