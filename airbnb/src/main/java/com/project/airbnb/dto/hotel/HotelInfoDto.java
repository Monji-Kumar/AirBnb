package com.project.airbnb.dto.hotel;

import com.project.airbnb.dto.room.RoomDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HotelInfoDto {
    private HotelDto hotelDto;
    private List<RoomDto> roomDto;
}
