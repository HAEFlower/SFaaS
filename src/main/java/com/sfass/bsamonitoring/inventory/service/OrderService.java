package com.sfass.bsamonitoring.inventory.service;

import com.sfass.bsamonitoring.inventory.dto.request.OrderRequestDTO;
import com.sfass.bsamonitoring.inventory.dto.response.OrderResponseDTO;
import com.sfass.bsamonitoring.inventory.mapper.OrderMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderMapper orderMapper;

    @Transactional
    public OrderResponseDTO createOrder(OrderRequestDTO request) {
        // 주문 ID 생성 (ORD-YYYYMMDD-###)
        String orderId = generateOrderId();

        // 재고 수량 업데이트
        orderMapper.updateInventoryQuantity(request.getProductCode(), request.getQuantity());

        // 업데이트된 수량 조회
        Integer updatedQuantity = orderMapper.getCurrentQuantity(request.getProductCode());

        return OrderResponseDTO.builder()
                .orderId(orderId)
                .productCode(request.getProductCode())
                .quantity(request.getQuantity())
                .orderTime(LocalDateTime.now())
                .updatedQuantity(updatedQuantity)
                .build();
    }

    private String generateOrderId() {
        String dateStr = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        int sequence = orderMapper.getNextOrderSequence();
        return String.format("ORD-%s-%03d", dateStr, sequence);
    }
}