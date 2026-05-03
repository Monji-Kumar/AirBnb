package com.project.airbnb.entity.inventory;

import com.project.airbnb.entity.hotel.Hotel;
import com.project.airbnb.entity.room.Room;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {

    void deleteByDateAfterAndRoom(LocalDate date, Room room);

    void deleteByRoom(Room room);

    @Query("""
            SELECT DISTINCT i.hotel
            FROM Inventory i
            WHERE i.city = :city
                AND i.date BETWEEN :startDate AND :endDate
                AND i.closed = false
                AND (i.totalCount - i.bookedCount) >= :roomCount 
            GROUP BY i.hotel, i.room
            HAVING COUNT(i.date) = :dateCount
            """)
    Page<Hotel> findHotelsWithAvailableInventory(@Param("city") String city,
                                                 @Param("startDate") LocalDate startDate,
                                                 @Param("endDate") LocalDate endDate,
                                                 @Param("roomCount") Integer roomsCount,
                                                 @Param("dateCount") Long dateCount,
                                                 Pageable pageable);
}
