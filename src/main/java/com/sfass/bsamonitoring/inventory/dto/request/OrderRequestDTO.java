package com.sfass.bsamonitoring.inventory.dto.request;

import lombok.Data;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@Data
public class OrderRequestDTO {
    @NotNull(message = "생산라인 ID는 필수입니다")
    private Long productId;

    @NotNull(message = "공정라인 ID는 필수입니다")
    private Long processId;

    @NotNull(message = "부품 ID는 필수입니다")
    private Long productCode;

    @Min(value = 1, message = "발주 수량은 0보다 커야 합니다")
    private Integer quantity;
}