package com.project.airbnb.controller;

import com.project.airbnb.advice.ApiResponse;
import com.project.airbnb.dto.hotel.HotelDto;
import com.project.airbnb.dto.hotel.HotelRequestDto;
import com.project.airbnb.service.inventory.InventoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping(value = "/hotels")
@RequiredArgsConstructor
@Slf4j
public class HotelBrowseController {

    private final InventoryService inventoryService;

    @GetMapping(value = "/search")
    public ResponseEntity<?> searchHotels(@RequestBody HotelRequestDto dto) {
        Page<HotelDto> hotels = inventoryService.searchHotels(dto);
        return new ResponseEntity<>(new ApiResponse<>(hotels), HttpStatus.OK);
    }
}
