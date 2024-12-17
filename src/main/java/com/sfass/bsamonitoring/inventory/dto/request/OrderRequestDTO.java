package com.sfass.bsamonitoring.inventory.dto.request;

import lombok.Data;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

@Data
public class OrderRequestDTO {
    @NotBlank(message = "제품 코드는 필수입니다")
    private String productCode;

    @Min(value = 1, message = "발주 수량은 0보다 커야 합니다")
    private Integer quantity;
}