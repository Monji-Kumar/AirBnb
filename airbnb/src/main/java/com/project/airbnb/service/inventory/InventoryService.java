package com.project.airbnb.service.inventory;

import com.project.airbnb.dto.hotel.HotelDto;
import com.project.airbnb.dto.hotel.HotelRequestDto;
import com.project.airbnb.entity.room.Room;
import org.springframework.data.domain.Page;

public interface InventoryService {
    void initializeRoomForAYear(Room room);

    void deleteFutureInventories(Room room);

    void deleteAllInventories(Room room);

    Page<HotelDto> searchHotels(HotelRequestDto dto);
}
