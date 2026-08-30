package com.project.airbnb.dto.booking;

import com.project.airbnb.dto.guest.GuestDto;
import com.project.airbnb.dto.hotel.HotelDto;
import com.project.airbnb.dto.room.RoomDto;
import com.project.airbnb.dto.user.UserDto;
import com.project.airbnb.enums.BookingStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookingDto {
    private Long id;
    private HotelDto hotel;
    private RoomDto room;
    private UserDto user;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private BookingStatus status;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private BookingStatus bookingStatus;
    private Set<GuestDto> guests;
}
