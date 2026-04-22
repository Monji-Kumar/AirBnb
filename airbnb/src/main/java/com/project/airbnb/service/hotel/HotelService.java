package com.project.airbnb.service.hotel;

import com.project.airbnb.dto.hotel.HotelDto;
import com.project.airbnb.entity.hotel.Hotel;

public interface HotelService {

    HotelDto createNewHotel(HotelDto dto);

    HotelDto getHotelById(Long id);
}
