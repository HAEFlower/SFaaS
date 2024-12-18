package com.sfass.bsamonitoring.inventory.controller;

import com.sfass.bsamonitoring.inventory.dto.request.InventorySearchDTO;
import com.sfass.bsamonitoring.inventory.dto.response.InventoryDTO;
import com.sfass.bsamonitoring.inventory.dto.response.InventoryListResponse;
import com.sfass.bsamonitoring.inventory.service.InventoryService;
import com.sfass.bsamonitoring.global.GlobalResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/inventory")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;

    @GetMapping("/items")
    public ResponseEntity<GlobalResponse> getInventoryItems(InventorySearchDTO searchDTO) {
        log.info("Searching inventory with criteria: {}", searchDTO);
        List<InventoryDTO> items = inventoryService.getInventoryItems(searchDTO);
        Long totalItems = inventoryService.countInventoryItems(searchDTO);
        log.error(searchDTO.toString());

        return ResponseEntity.ok(GlobalResponse.ok(new InventoryListResponse(items, totalItems)));
    }
}