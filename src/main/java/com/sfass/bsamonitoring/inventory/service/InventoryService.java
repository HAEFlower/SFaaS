package com.sfass.bsamonitoring.inventory.service;

import com.sfass.bsamonitoring.inventory.dto.request.InventorySearchDTO;
import com.sfass.bsamonitoring.inventory.dto.response.InventoryDTO;
import com.sfass.bsamonitoring.inventory.mapper.InventoryMapper;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor

public class InventoryService {

    private final InventoryMapper inventoryMapper;
    @Transactional(readOnly = true)
    public List<InventoryDTO> getInventoryItems(InventorySearchDTO searchDTO) {
        log.debug("Fetching inventory items with criteria: {}", searchDTO);
        return inventoryMapper.selectInventoryItems(searchDTO);
    }

    public Long countInventoryItems(InventorySearchDTO searchDTO) {
        return inventoryMapper.countInventoryItems(searchDTO);
    }
}