package com.sfass.bsamonitoring.inventory.mapper;

import com.sfass.bsamonitoring.inventory.dto.request.InventorySearchDTO;
import com.sfass.bsamonitoring.inventory.dto.response.InventoryDTO;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface InventoryMapper {
    List<InventoryDTO> selectInventoryItems(InventorySearchDTO searchDTO);
    Long countInventoryItems(InventorySearchDTO searchDTO);
}