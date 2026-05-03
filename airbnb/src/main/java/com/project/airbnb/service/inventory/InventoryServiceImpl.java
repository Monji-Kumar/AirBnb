package com.project.airbnb.service.inventory;

import com.project.airbnb.config.modelmapper.MapperConfig;
import com.project.airbnb.dto.hotel.HotelDto;
import com.project.airbnb.dto.hotel.HotelRequestDto;
import com.project.airbnb.entity.hotel.Hotel;
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
    private final MapperConfig mapperConfig;

    @Override
    public void initializeRoomForAYear(Room room) {
        LocalDate today = LocalDate.now();
        LocalDate endDate = today.plusYears(1);
        for(; !today.isAfter(endDate) ; today = today.plusDays(1)) {
            Inventory inventory = Inventory.builder()
                    .hotel(room.getHotel())
                    .room(room)
                    .city(room.getHotel().getCity())
                    .price(room.getBasePrice())
                    .surgeFactor(BigDecimal.ONE)
                    .totalCount(room.getTotalCount())
                    .closed(false)
                    .date(LocalDate.now())
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
    public Page<HotelDto> searchHotels(HotelRequestDto dto) {
        Pageable pageable = PageRequest.of(dto.getPage(), dto.getPageSize());

        long dateCount = ChronoUnit.DAYS.between(dto.getStartDate(), dto.getEndDate()) + 1;
        Page<Hotel> hotels = inventoryRepository.findHotelsWithAvailableInventory(dto.getCity(), dto.getStartDate(), dto.getEndDate(),
                dto.getRoomCount(), dateCount, pageable);

        return hotels.map((element) -> mapperConfig.modelMapper().map(element, HotelDto.class));
    }
}
