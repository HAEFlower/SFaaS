package com.sfass.bsamonitoring.inventory.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface OrderMapper {
    void updateInventoryQuantity(@Param("productCode") String productCode,
                                 @Param("quantity") Integer quantity,
                                 @Param("productId") String productId,
                                 @Param("processId") String processId);

    Integer getCurrentQuantity(@Param("productCode") String productCode,
                               @Param("productId") String productId,
                               @Param("processId") String processId);

    int getNextOrderSequence();

    void insertOrderSequence(int sequence);
}