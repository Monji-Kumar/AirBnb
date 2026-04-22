package com.project.airbnb.service.hotel;

import com.project.airbnb.dto.hotel.HotelDto;
import com.project.airbnb.entity.hotel.Hotel;
import com.project.airbnb.entity.hotel.HotelRepository;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class HotelServiceImpl implements HotelService {

    private static final Logger log = LoggerFactory.getLogger(HotelServiceImpl.class);
    private final HotelRepository hotelRepository;

    public HotelServiceImpl(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

    @Override
    public HotelDto createNewHotel(HotelDto dto) {
        Hotel hotel = new Hotel();
        hotel.setHotelName(dto.getHotelName());
        hotel.setActive(true);
        hotel.setCity(dto.getCity());
        hotel.setAmenities(dto.getAmenities());
        hotel.setContactInfo(dto.getContactInfo());
        hotel.setPhotoes(dto.getPhotoes());
        hotel = hotelRepository.save(hotel);
        log.info("new Hotel Creation started - " + dto.getHotelName());
        return getHotelDtoFromHotel(hotel);
    }

    @Override
    public HotelDto getHotelById(Long id) {
        Optional<Hotel> hotelOpt = hotelRepository.findById(id);
        if(hotelOpt.isEmpty()) {
            throw new RuntimeException("No Hotel found with the given ID - " + id);
        }

        log.info("new Hotel Creation started - " + hotelOpt.get().getHotelName());
        return getHotelDtoFromHotel(hotelOpt.get());
    }

    private HotelDto getHotelDtoFromHotel(Hotel hotel) {
        HotelDto dto = new HotelDto();
        dto.setHotelName(hotel.getHotelName());
        dto.setActive(hotel.getActive());
        dto.setCity(hotel.getCity());
        dto.setAmenities(hotel.getAmenities());
        dto.setContactInfo(hotel.getContactInfo());
        dto.setPhotoes(hotel.getPhotoes());
        dto.setId(hotel.getId());
        dto.setCreatedAt(hotel.getCreatedAt());
        dto.setUpdatedAt(hotel.getUpdatedAt());
        return dto;
    }
}
