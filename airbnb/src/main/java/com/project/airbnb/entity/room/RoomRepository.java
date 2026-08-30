package com.project.airbnb.entity.room;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RoomRepository extends JpaRepository<Room, Long> {
    List<Room> findAllByHotelId(Long hotelId);

    Optional<Room> findByHotelIdAndId(Long hotelId, Long Id);
}
