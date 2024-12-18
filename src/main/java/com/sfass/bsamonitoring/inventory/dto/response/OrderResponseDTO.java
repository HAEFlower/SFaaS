package com.sfass.bsamonitoring.inventory.dto.response;

import lombok.Builder;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Builder
public class OrderResponseDTO {
    private String orderId;
    private String productId;
    private String processId;
    private String productCode;
    private Integer quantity;
    private LocalDateTime orderTime;
    private Integer updatedQuantity;
}