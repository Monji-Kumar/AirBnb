package com.project.airbnb.dto.hotel;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@Data
@RequiredArgsConstructor
public class HotelRequestDto {
    private String city;
    private LocalDate startDate;
    private LocalDate endDate;

    private Integer roomCount;
    private Integer page = 0;
    private Integer pageSize = 10;
}
