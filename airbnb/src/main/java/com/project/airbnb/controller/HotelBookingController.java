package com.project.airbnb.controller;

import com.project.airbnb.dto.booking.BookingDto;
import com.project.airbnb.dto.booking.BookingRequestDto;
import com.project.airbnb.dto.guest.GuestDto;
import com.project.airbnb.dto.hotel.HotelDto;
import com.project.airbnb.service.booking.BookingService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/bookings")
public class HotelBookingController {

    private final BookingService bookingService;

    @PostMapping(value = "/init")
    public ResponseEntity<?> initializeBooking(@RequestBody BookingRequestDto bookingDto) {
        return ResponseEntity.ok(bookingService.intializeBooking(bookingDto));
    }

    @PostMapping(value = "/{bookingId}/add-guests")
    public ResponseEntity<?> addGuests(@PathVariable("bookingId") long bookingId, @RequestBody List<GuestDto> guestList) {
        return ResponseEntity.ok(bookingService.addGuests(bookingId, guestList));
    }
}
