package com.sfass.bsamonitoring.inventory.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InventoryDTO {
    private String name;                 // 제품명
    private Long productCode;            // 부품 ID
    private String productionLine;       // 생산라인
    private String processCode;          // 공정코드
    private String lastUpdate;           // 입고날짜
    private Integer currentQuantity;     // 현재수량
    private Integer minimumQuantity;     // 최소필요수량
}