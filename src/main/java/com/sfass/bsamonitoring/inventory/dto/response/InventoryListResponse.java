package com.sfass.bsamonitoring.inventory.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InventoryListResponse {
    private List<InventoryDTO> items;    // 재고 아이템 목록
    private Long totalItems;             // 전체 아이템 수
}