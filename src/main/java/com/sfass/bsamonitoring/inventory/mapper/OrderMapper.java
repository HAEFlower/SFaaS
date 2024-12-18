package com.sfass.bsamonitoring.inventory.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface OrderMapper {
    void updateInventoryQuantity(@Param("productCode") String productCode,
                                 @Param("quantity") Integer quantity);

    Integer getCurrentQuantity(@Param("productCode") String productCode);

    int getNextOrderSequence();

    void insertOrderSequence(int sequence);
}