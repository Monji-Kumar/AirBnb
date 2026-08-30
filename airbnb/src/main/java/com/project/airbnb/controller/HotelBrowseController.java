package com.project.airbnb.controller;

import com.project.airbnb.advice.ApiResponse;
import com.project.airbnb.dto.hotel.HotelDto;
import com.project.airbnb.dto.hotel.HotelInfoDto;
import com.project.airbnb.dto.hotel.HotelSearchRequestDto;
import com.project.airbnb.service.hotel.HotelService;
import com.project.airbnb.service.inventory.InventoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping(value = "/hotels")
@RequiredArgsConstructor
@Slf4j
public class HotelBrowseController {

    private final InventoryService inventoryService;
    private final HotelService hotelService;

    @GetMapping(value = "/search")
    public ResponseEntity<?> searchHotels(@RequestBody HotelSearchRequestDto dto) {
        var page = inventoryService.searchHotels(dto);
        return new ResponseEntity<>(new ApiResponse<>(page), HttpStatus.OK);
    }

    @GetMapping(value = "/{hotelId}/info")
    public ResponseEntity<?> getHotelById(@PathVariable Long hotelId) {
        return new ResponseEntity<>(hotelService.getHotelInfoById(hotelId), HttpStatus.OK);
    }
}
