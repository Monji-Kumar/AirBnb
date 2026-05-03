package com.project.airbnb.service.hotel;

import com.project.airbnb.config.modelmapper.MapperConfig;
import com.project.airbnb.dto.hotel.HotelDto;
import com.project.airbnb.entity.hotel.Hotel;
import com.project.airbnb.entity.hotel.HotelRepository;
import com.project.airbnb.exception.ResourceNotFoundException;
import com.project.airbnb.service.inventory.InventoryService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class HotelServiceImpl implements HotelService {

    private final HotelRepository hotelRepository;
    private final MapperConfig mapperConfig;
    private final InventoryService inventoryService;

    @Override
    public Hotel saveHotel(Hotel hotel) {
        return hotelRepository.save(hotel);
    }

    @Override
    public HotelDto createNewHotel(HotelDto dto) {
        Hotel hotel = new Hotel();
        hotel = mapperConfig.modelMapper().map(dto, Hotel.class);
        hotel.setIsActive(false);
        saveHotel(hotel);
        log.info("new Hotel Creation started - " + dto.getHotelName());
        return getHotelDtoFromHotel(hotel);
    }

    @Override
    public HotelDto getHotelById(Long id) {
        Optional<Hotel> hotelOpt = hotelRepository.findById(id);
        if(hotelOpt.isEmpty()) {
            throw new ResourceNotFoundException("No Hotel found with the given ID - " + id);
        }

        log.info("Getting Hotel with ID - " + id + " with name - " + hotelOpt.get().getHotelName());
        return getHotelDtoFromHotel(hotelOpt.get());
    }

    @Override
    public List<HotelDto> getAllHotels() {
        List<Hotel> hotelList = hotelRepository.findAll();
        return hotelList.stream().map(this::getHotelDtoFromHotel).toList();
    }

    private HotelDto getHotelDtoFromHotel(Hotel hotel) {
        HotelDto dto = new HotelDto();
        dto = mapperConfig.modelMapper().map(hotel, HotelDto.class);
        return dto;
    }

    @Override
    public HotelDto updateHotelById(Long id, HotelDto hotelDto) {
        Hotel hotel = hotelRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No Hotel Found with the give ID - " + id));
        log.info("Updating the Hotel with ID - " + id + " with name - " + hotel.getHotelName());
        mapperConfig.modelMapper().map(hotelDto, hotel);
        hotel.setId(id);
        hotel = saveHotel(hotel);
        return mapperConfig.modelMapper().map(hotel, HotelDto.class);
    }

    @Transactional(rollbackOn = Throwable.class)
    @Override
    public Boolean deleteHotelById(Long id) {
        Hotel hotel = hotelRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No Hotel Found with the give ID - " + id));
        log.info("Deleting the Hotel with ID - " + id + " with name - " + hotel.getHotelName());


        //TODO:delete inventories of hotel as well
        hotel.getRooms().forEach(inventoryService::deleteAllInventories);

        hotelRepository.delete(hotel);
        return true;
    }

    @Override
    public Hotel findHotelById(Long id) {
        return hotelRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No Hotel Found with the given ID -" + id));
    }

    @Override
    public Boolean hotelExistsById(Long id) {
        return hotelRepository.existsById(id);
    }

    @Transactional(rollbackOn = Throwable.class)
    @Override
    public Boolean activateHotel(Long id, Boolean activate) {
        Hotel hotel = hotelRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No Hotel Found with the given ID - " + id));
        log.info((activate ? "Activated " : "De-Activated ") + " Hotel with ID - " + id + " and name - " + hotel.getHotelName());
        hotel.setIsActive(activate);
        // TODO: Create inventory of Hotel
        saveHotel(hotel);

        //First time activation of hotel
        hotel.getRooms().forEach(inventoryService::initializeRoomForAYear);
        return true;
    }

    @Override
    public Boolean existsByService(Long id) {
        return hotelRepository.existsById(id);
    }
}
