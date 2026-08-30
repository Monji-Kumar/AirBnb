package com.project.airbnb.service.inventory;

import com.project.airbnb.config.modelmapper.MapperConfig;
import com.project.airbnb.dto.hotel.HotelDto;
import com.project.airbnb.dto.hotel.HotelPriceDto;
import com.project.airbnb.dto.hotel.HotelSearchRequestDto;
import com.project.airbnb.entity.hotel.Hotel;
import com.project.airbnb.entity.hotel.hotelminprice.HotelMinPrice;
import com.project.airbnb.entity.hotel.hotelminprice.HotelMinPriceRepository;
import com.project.airbnb.entity.inventory.Inventory;
import com.project.airbnb.entity.inventory.InventoryRepository;
import com.project.airbnb.entity.room.Room;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Service
@RequiredArgsConstructor
@Slf4j
public class InventoryServiceImpl implements InventoryService {
    private final InventoryRepository inventoryRepository;
    private final HotelMinPriceRepository hotelMinPriceRepository;
    private final MapperConfig mapperConfig;

    @Override
    public void initializeRoomForAYear(Room room) {
        LocalDate today = LocalDate.now();
        LocalDate endDate = today.plusYears(1);
        for(; !today.isAfter(endDate) ; today = today.plusDays(1)) {
            Inventory inventory = Inventory.builder()
                    .hotel(room.getHotel())
                    .room(room)
                    .bookedCount(0)
                    .reservedCount(0)
                    .city(room.getHotel().getCity())
                    .price(room.getBasePrice())
                    .surgeFactor(BigDecimal.ONE)
                    .totalCount(room.getTotalCount())
                    .closed(false)
                    .date(today)
                    .build();

            inventoryRepository.save(inventory);
        }
    }

    @Transactional(rollbackOn = Throwable.class)
    @Override
    public void deleteFutureInventories(Room room) {
        LocalDate today = LocalDate.now();
        inventoryRepository.deleteByDateAfterAndRoom(today, room);
    }

    @Override
    public void deleteAllInventories(Room room) {
        inventoryRepository.deleteByRoom(room);
    }

    @Override
    public Page<?> searchHotels(HotelSearchRequestDto dto) {
        Pageable pageable = PageRequest.of(dto.getPage(), dto.getPageSize());

        long dateCount = ChronoUnit.DAYS.between(dto.getStartDate(), dto.getEndDate()) + 1;

        //first 90 days
        LocalDate today = LocalDate.now();
        if(dto.getStartDate().isAfter(today.plusDays(90))) {
            return hotelMinPriceRepository.findHotelsWithAvailableInventory(dto.getCity(), dto.getStartDate(), dto.getEndDate(), pageable);
        } else {
            Page<Hotel> hotels = inventoryRepository.findHotelsWithAvailableInventory(dto.getCity(), dto.getStartDate(), dto.getEndDate(),dto.getRoomCount(),dateCount, pageable);
            return hotels.map((element) -> mapperConfig.modelMapper().map(element, HotelDto.class));
        }


    }
}
