package com.project.airbnb.dto.inventory;

import com.project.airbnb.dto.hotel.HotelDto;
import com.project.airbnb.dto.room.RoomDto;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class InventoryDto {

    private Long id;

    private HotelDto hotel;

    private RoomDto room;

    private LocalDate bookingDate;

    private Integer totalCount;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private Double surgeFactor;

    private Boolean closed;
}
