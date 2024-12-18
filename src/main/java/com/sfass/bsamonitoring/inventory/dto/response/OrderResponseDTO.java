package com.sfass.bsamonitoring.inventory.dto.response;

import lombok.Builder;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Builder
public class OrderResponseDTO {
    private String orderId;
    private Long productId;
    private Long processId;
    private Long partId;
    private Integer quantity;
    private LocalDateTime orderTime;
    private Integer updatedQuantity;
}