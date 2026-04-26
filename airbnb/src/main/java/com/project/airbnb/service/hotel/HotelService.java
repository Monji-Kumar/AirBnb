package com.project.airbnb.service.hotel;

import com.project.airbnb.dto.hotel.HotelDto;
import com.project.airbnb.entity.hotel.Hotel;

import java.util.List;

public interface HotelService {

    Hotel saveHotel(Hotel hotel);

    HotelDto createNewHotel(HotelDto dto);

    HotelDto getHotelById(Long id);

    List<HotelDto> getAllHotels();

    HotelDto updateHotelById(Long Id, HotelDto hotelDto);

    Boolean deleteHotelById(Long id);

    Boolean activateHotel(Long id, Boolean activte);
}
