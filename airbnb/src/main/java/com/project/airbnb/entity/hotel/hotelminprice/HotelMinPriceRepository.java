package com.project.airbnb.entity.hotel.hotelminprice;

import com.project.airbnb.dto.hotel.HotelPriceDto;
import com.project.airbnb.entity.hotel.Hotel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.Optional;

public interface HotelMinPriceRepository extends JpaRepository<HotelMinPrice, Integer> {
    @Query("""
            SELECT new com.project.airbnb.dto.hotel.HotelPriceDto(hmp.hotel, AVG(hmp.price))
            FROM HotelMinPrice hmp
            WHERE hmp.hotel.city = :city
                AND hmp.date BETWEEN :startDate AND :endDate
                AND hmp.hotel.active = false
            GROUP BY hmp.hotel
            """)
    Page<HotelPriceDto> findHotelsWithAvailableInventory(@Param("city") String city,
                                                         @Param("startDate") LocalDate startDate,
                                                         @Param("endDate") LocalDate endDate,
                                                         Pageable pageable);

    Optional<HotelMinPrice> findByHotelAndDate(Hotel hotel, LocalDate date);
}
