package com.project.airbnb.controller;

import com.project.airbnb.dto.hotel.HotelDto;
import com.project.airbnb.entity.hotel.Hotel;
import com.project.airbnb.service.hotel.HotelService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping(value = "/admin/hotels")
@Slf4j
@RequiredArgsConstructor
public class HotelController {

    public final HotelService hotelService;

    @PostMapping(value = "/create-hotel")
    public ResponseEntity<?> createHotel(@RequestBody HotelDto hotelDto) {
        log.info("Attempting to create a new Hotel with hotel name - " + hotelDto.getHotelName());
        HotelDto dto = hotelService.createNewHotel(hotelDto);
        log.info("Hotel - " + hotelDto.getHotelName() + " created successfully");
        return new ResponseEntity<>(dto,HttpStatus.CREATED);
    }

    @GetMapping(value = "/get-hotel")
    public ResponseEntity<?> getHotelByID(@RequestParam(value = "id") Long id) {
        return new ResponseEntity<>(hotelService.getHotelById(id), HttpStatus.FOUND);
    }

    @GetMapping(value = "get-all-hotels")
    public ResponseEntity<?> getAllHotels() {
        return new ResponseEntity<>(hotelService.getAllHotels(), HttpStatus.FOUND);
    }

    @PutMapping(value = "update-hotel")
    public ResponseEntity<?> updatehotelById(@RequestParam(value = "id") Long id,
                                             @RequestBody HotelDto hotelDto) {
        return new ResponseEntity<>(hotelService.updateHotelById(id, hotelDto), HttpStatus.ACCEPTED);
    }

    @DeleteMapping(value = "delete-hotel")
    public ResponseEntity<?> deleteHotelById(@RequestParam(value = "id") Long id) {
        return new ResponseEntity<>(hotelService.deleteHotelById(id), HttpStatus.NO_CONTENT);
    }

    @PatchMapping(value ="activate-deactivate-hotel")
    public ResponseEntity<?> activateHotel(@RequestParam(value = "id") Long id, @RequestBody Map<String, Object> requestBody) {
        return new ResponseEntity<>(hotelService.activateHotel(id, (Boolean) requestBody.get("activate")), HttpStatus.ACCEPTED);
    }
}
