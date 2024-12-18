package com.sfass.bsamonitoring.inventory.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface OrderMapper {
    void updateInventoryQuantity(@Param("productId") Long productId,
                                 @Param("processId") Long processId,
                                 @Param("partId") Long partId,
                                 @Param("quantity") Integer quantity);

    Integer getCurrentQuantity(@Param("productId") Long productId,
                               @Param("processId") Long processId,
                               @Param("partId") Long partId);

    boolean checkProcessLineExists(@Param("productId") Long productId,
                                   @Param("processId") Long processId);

    int getNextOrderSequence();

    void insertOrderSequence(int sequence);
}