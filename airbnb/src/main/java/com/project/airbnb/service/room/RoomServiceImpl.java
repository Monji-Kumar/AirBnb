package com.project.airbnb.service.room;

import com.project.airbnb.config.modelmapper.MapperConfig;
import com.project.airbnb.dto.room.RoomDto;
import com.project.airbnb.entity.room.Room;
import com.project.airbnb.entity.room.RoomRepository;
import com.project.airbnb.exception.ResourceNotFoundException;
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

    @Override
    public RoomDto getRoomById(Long id) {
        Room room = roomRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No Room found with the given ID - " + id));
        return mapperConfig.modelMapper().map(room, RoomDto.class);
    }

    @Override
    public RoomDto createNewRoom(RoomDto roomDto) {
        Room room = mapperConfig.modelMapper().map(roomDto, Room.class);
        room = saveRoom(room);
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

    @Override
    public Boolean deleteRoomById(Long id) {
        boolean exists = roomRepository.existsById(id);
        if(!exists) {
            throw new ResourceNotFoundException("No Room exists with the given ID - " + id);
        }
        roomRepository.deleteById(id);
        return true;
    }

    @Override
    public Boolean updateStatusOfRoomById(Long id) {
        return null;
    }

    private RoomDto getRoomDtoByRoom(Room room) {
        return mapperConfig.modelMapper().map(room, RoomDto.class);
    }
}
