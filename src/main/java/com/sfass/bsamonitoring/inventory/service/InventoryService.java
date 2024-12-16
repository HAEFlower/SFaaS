package com.sfass.bsamonitoring.inventory.service;

import com.sfass.bsamonitoring.global.error.exception.CustomException;
import com.sfass.bsamonitoring.global.error.exception.ErrorCode;
import com.sfass.bsamonitoring.inventory.dto.request.InventorySearchDTO;
import com.sfass.bsamonitoring.inventory.dto.response.InventoryDTO;
import com.sfass.bsamonitoring.inventory.mapper.InventoryMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class InventoryService {

    private final InventoryMapper inventoryMapper;

    @Cacheable(value = "inventoryItems", key = "#searchDTO")
    public List<InventoryDTO> getInventoryItems(InventorySearchDTO searchDTO) {
        log.debug("Fetching inventory items with search criteria: {}", searchDTO);

        validateSearchCriteria(searchDTO);

        List<InventoryDTO> items = inventoryMapper.selectInventoryItems(searchDTO);

        if (items.isEmpty() && (searchDTO.getProcessId() != null || searchDTO.getProductId() != null)) {
            throw new CustomException(ErrorCode.INVENTORY_NOT_FOUND);
        }

        log.debug("Found {} inventory items", items.size());
        return items;
    }

    private void validateSearchCriteria(InventorySearchDTO searchDTO) {
        if (searchDTO.getProcessId() != null && !searchDTO.getProcessId().matches("^\\d+$")) {
            throw new CustomException(ErrorCode.QUERY_TYPE_MISMATCH);
        }

        if (searchDTO.getProductId() != null && !searchDTO.getProductId().matches("^\\d+$")) {
            throw new CustomException(ErrorCode.QUERY_TYPE_MISMATCH);
        }
    }
}