package com.project.airbnb.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController(value = "/api/hotel")
public class HotelController {

    @PostMapping(value = "/create-hotel")
    public ResponseEntity<?> createHotel() {
        return ResponseEntity.ok().build();
    }
}
