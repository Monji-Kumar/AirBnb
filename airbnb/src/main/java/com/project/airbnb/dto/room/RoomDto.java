package com.project.airbnb.dto.room;

import com.project.airbnb.entity.hotel.Hotel;
import com.project.airbnb.enums.BookingStatus;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class RoomDto {

    private Long id;

    private Hotel hotel;

    private String type;

    private Double basePrice;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private List<String> amenities;

    private List<String> photoes;

    private Integer totalCount;

    private Integer capacity;

    private BookingStatus status;
}
