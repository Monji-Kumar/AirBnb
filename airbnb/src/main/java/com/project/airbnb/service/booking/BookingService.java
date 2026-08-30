package com.project.airbnb.service.booking;

import com.project.airbnb.dto.booking.BookingDto;
import com.project.airbnb.dto.booking.BookingRequestDto;
import com.project.airbnb.dto.guest.GuestDto;

import java.util.List;

public interface BookingService {

    BookingDto intializeBooking(BookingRequestDto bookingRequestDto);

    BookingDto addGuests(long bookingId, List<GuestDto> guestList);
}
