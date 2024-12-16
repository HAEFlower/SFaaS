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
    private String name;
    private String productCode;
    private String productionLine;
    private String processCode;
    private String lastUpdate;
    private Integer currentQuantity;
    private Integer minimumQuantity;
}