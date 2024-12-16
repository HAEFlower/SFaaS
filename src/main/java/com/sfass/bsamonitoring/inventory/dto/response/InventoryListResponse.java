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
    private List<InventoryDTO> items;
    private Integer totalItems;
}