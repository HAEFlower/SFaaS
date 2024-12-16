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

    public List<InventoryDTO> getInventoryItems(InventorySearchDTO searchDTO) {
        log.debug("Fetching inventory items with search criteria: {}", searchDTO);

        validateSearchCriteria(searchDTO);

        List<InventoryDTO> items = inventoryMapper.selectInventoryItems(searchDTO);

        // 검색 결과를 로그로 기록
        if (items.isEmpty()) {
            log.info("No inventory items found for search criteria: {}", searchDTO);
        }

        return items; // 빈 리스트 반환
    }

    private void validateSearchCriteria(InventorySearchDTO searchDTO) {
        // 검색 조건 유효성 검사
        if (searchDTO.getProcessId() != null && !searchDTO.getProcessId().matches("^\\d+$")) {
            throw new CustomException(ErrorCode.QUERY_TYPE_MISMATCH);
        }

        if (searchDTO.getProductId() != null && !searchDTO.getProductId().matches("^\\d+$")) {
            throw new CustomException(ErrorCode.QUERY_TYPE_MISMATCH);
        }
    }
}