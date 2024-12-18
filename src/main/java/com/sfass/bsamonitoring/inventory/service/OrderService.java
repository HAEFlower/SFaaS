package com.sfass.bsamonitoring.inventory.service;

import com.sfass.bsamonitoring.inventory.dto.request.OrderRequestDTO;
import com.sfass.bsamonitoring.inventory.dto.response.OrderResponseDTO;
import com.sfass.bsamonitoring.inventory.mapper.OrderMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderMapper orderMapper;

    @Transactional
    public OrderResponseDTO createOrder(OrderRequestDTO request) {
        // Validate production line process exists
        if (!orderMapper.checkProcessLineExists(request.getProductId(), request.getProcessId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Invalid production line and process combination");
        }

        // Get and insert next sequence
        int sequence = orderMapper.getNextOrderSequence();
        orderMapper.insertOrderSequence(sequence);

        // Generate order ID (ORD-YYYYMMDD-###)
        String orderId = String.format("ORD-%s-%03d",
                LocalDateTime.now().format(DateTimeFormatter.BASIC_ISO_DATE),
                sequence);

        // Update inventory
        orderMapper.updateInventoryQuantity(
                request.getProductId(),
                request.getProcessId(),
                request.getPartId(),
                request.getQuantity()
        );

        // Get updated quantity
        Integer updatedQuantity = orderMapper.getCurrentQuantity(
                request.getProductId(),
                request.getProcessId(),
                request.getPartId()
        );

        // Build response
        return OrderResponseDTO.builder()
                .orderId(orderId)
                .productId(request.getProductId())
                .processId(request.getProcessId())
                .partId(request.getPartId())
                .quantity(request.getQuantity())
                .orderTime(LocalDateTime.now())
                .updatedQuantity(updatedQuantity)
                .build();
    }
}