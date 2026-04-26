package com.project.airbnb.service.room;

import com.project.airbnb.dto.room.RoomDto;
import com.project.airbnb.entity.room.Room;

import java.util.List;

public interface RoomService {
    RoomDto getRoomById(Long id);

    RoomDto createNewRoom(Long hotelId, RoomDto roomDto);

    Room saveRoom(Room room);

    List<RoomDto> getAllRooms();

    Boolean deleteRoomById(Long id);

    Boolean updateStatusOfRoomById(Long id, String status);

    List<RoomDto> getAllRoomsInHotel(Long hotelId);
}
