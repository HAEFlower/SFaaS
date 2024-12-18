package com.sfass.bsamonitoring.inventory.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface OrderMapper {
    void updateInventoryQuantity(@Param("partId") Long partId,
                                 @Param("productId") String productId,
                                 @Param("processId") String processId,
                                 @Param("quantity") Integer quantity);

    Integer getCurrentQuantity(@Param("partId") Long partId,
                               @Param("productId") String productId,
                               @Param("processId") String processId);

    int getNextOrderSequence();

    void insertOrderSequence(int sequence);
}
