package com.project.airbnb.service.room;

import com.project.airbnb.config.modelmapper.MapperConfig;
import com.project.airbnb.dto.room.RoomDto;
import com.project.airbnb.entity.hotel.Hotel;
import com.project.airbnb.entity.room.Room;
import com.project.airbnb.entity.room.RoomRepository;
import com.project.airbnb.enums.BookingStatus;
import com.project.airbnb.exception.ResourceNotFoundException;
import com.project.airbnb.service.hotel.HotelService;
import com.project.airbnb.service.inventory.InventoryService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class RoomServiceImpl implements RoomService{

    public final RoomRepository roomRepository;

    public final MapperConfig mapperConfig;

    public final HotelService hotelService;

    public final InventoryService inventoryService;

    @Override
    public RoomDto getRoomById(Long id) {
        Room room = roomRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No Room found with the given ID - " + id));
        return mapperConfig.modelMapper().map(room, RoomDto.class);
    }

    @Transactional(rollbackOn = Throwable.class)
    @Override
    public RoomDto createNewRoom(Long hotelId, RoomDto roomDto) {
        Hotel hotel = hotelService.findHotelById(hotelId);
        roomDto.setHotel(hotel);
        Room room = mapperConfig.modelMapper().map(roomDto, Room.class);
        room = saveRoom(room);

        //TODO: create inventory
        if(hotel.getIsActive()) {
            inventoryService.initializeRoomForAYear(room);
        }
        return mapperConfig.modelMapper().map(room, RoomDto.class);
    }

    @Override
    public Room saveRoom(Room room) {
        return roomRepository.save(room);
    }

    @Override
    public List<RoomDto> getAllRooms() {
        List<Room> roomList = roomRepository.findAll();
        return roomList.stream().map(this::getRoomDtoByRoom).toList();
    }

    @Transactional(rollbackOn = Throwable.class)
    @Override
    public Boolean deleteRoomById(Long id) {
        boolean exists = roomRepository.existsById(id);
        if(!exists) {
            throw new ResourceNotFoundException("No Room exists with the given ID - " + id);
        }

        //Delete Inventories
        inventoryService.deleteAllInventories(roomRepository.findById(id).get());

        //Delete Room
        roomRepository.deleteById(id);
        return true;
    }

    @Override
    public Boolean updateStatusOfRoomById(Long id, String status) {
        Room room = roomRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No Room found with the given ID - " + id));
        BookingStatus bookingStatus = BookingStatus.valueOf(status.toUpperCase());
        room.setStatus(bookingStatus);
        roomRepository.save(room);
        return true;
    }

    @Override
    public List<RoomDto> getAllRoomsInHotel(Long hotelId) {
        List<Room> rooms = roomRepository.findAllByHotelId(hotelId);
        return rooms.stream().map(this::getRoomDtoByRoom).toList();
    }

    @Override
    public void updateRoomInventory(Long id) {
        Room room = roomRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No Room found with the given ID - " + id));
        inventoryService.initializeRoomForAYear(room);
    }

    private RoomDto getRoomDtoByRoom(Room room) {
        return mapperConfig.modelMapper().map(room, RoomDto.class);
    }

    @Override
    public Room findbyId(Long id) {
        return roomRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No Room found with the given ID - " + id));
    }

    @Override
    public Room findByHotelIdAndRoomId(Long hotelId, Long roomId) {
        return roomRepository.findByHotelIdAndId(hotelId, roomId).orElseThrow(() -> new ResourceNotFoundException("No Room found with the given HotelId - "+ hotelId +" and room ID - " + roomId));
    }
}
