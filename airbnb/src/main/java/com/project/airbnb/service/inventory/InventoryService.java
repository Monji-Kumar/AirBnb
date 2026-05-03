package com.project.airbnb.service.inventory;

import com.project.airbnb.entity.room.Room;

public interface InventoryService {
    void initializeRoomForAYear(Room room);

    void deleteFutureInventories(Room room);

    void deleteAllInventories(Room room);
}
