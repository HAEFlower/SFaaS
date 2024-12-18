package com.sfass.bsamonitoring.inventory.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Builder
public class OrderResponseDTO {
    private String orderId;
    private String productId;      // View에서는 String으로 반환
    private String processId;      // View에서는 String으로 반환
    private String productCode;    // partId를 productCode로 변환하여 반환
    private Integer quantity;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
    private LocalDateTime orderTime;
    private Integer updatedQuantity;
}