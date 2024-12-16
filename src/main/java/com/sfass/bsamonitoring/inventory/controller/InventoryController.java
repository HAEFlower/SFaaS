package com.sfass.bsamonitoring.inventory.controller;

import com.sfass.bsamonitoring.global.GlobalResponse;

import com.sfass.bsamonitoring.inventory.dto.response.ApiResponse;
import com.sfass.bsamonitoring.inventory.dto.request.InventorySearchDTO;
import com.sfass.bsamonitoring.inventory.dto.response.InventoryDTO;
import com.sfass.bsamonitoring.inventory.dto.response.InventoryListResponse;
import com.sfass.bsamonitoring.inventory.service.InventoryService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import java.util.List;

@Slf4j
@Validated
@RestController
@RequestMapping("/api/v1/inventory")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;

    @GetMapping("/items")
    public ResponseEntity<ApiResponse<InventoryListResponse>> getInventoryItems(
            @Valid @ModelAttribute InventorySearchDTO searchDTO) {
        log.info("Searching inventory with criteria: {}", searchDTO);
        List<InventoryDTO> items = inventoryService.getInventoryItems(searchDTO);

        InventoryListResponse response = InventoryListResponse.builder()
                .items(items)
                .totalItems(items.size())
                .build();

        return ResponseEntity.ok(ApiResponse.success(response));
    }
}