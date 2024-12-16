package com.sfass.bsamonitoring.inventory.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.Pattern;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InventorySearchDTO {
    @Pattern(regexp = "^[\\w\\s]*$", message = "검색어는 문자와 숫자만 허용됩니다")
    private String search;

    @Pattern(regexp = "^\\d*$", message = "processId는 숫자만 허용됩니다")
    private String processId;

    @Pattern(regexp = "^\\d*$", message = "productId는 숫자만 허용됩니다")
    private String productId;
}
