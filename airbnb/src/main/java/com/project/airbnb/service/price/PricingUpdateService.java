package com.project.airbnb.service.price;

import com.project.airbnb.entity.hotel.Hotel;
import com.project.airbnb.entity.hotel.HotelRepository;
import com.project.airbnb.entity.hotel.hotelminprice.HotelMinPrice;
import com.project.airbnb.entity.hotel.hotelminprice.HotelMinPriceRepository;
import com.project.airbnb.entity.inventory.Inventory;
import com.project.airbnb.entity.inventory.InventoryRepository;
import com.project.airbnb.strategy.PricingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.CollationElementIterator;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class PricingUpdateService {

    private final HotelRepository hotelRepository;
    private final InventoryRepository inventoryRepository;
    private final HotelMinPriceRepository hotelMinPriceRepository;
    private final PricingService pricingService;

    //Scheduler to update the inventory and Hotel min price every hour
    @Scheduled(fixedRate=1000)
    public void updatePricing(){
        int page = 0;
        int batchSize = 100;

        while(true){
            Page<Hotel> hotelPage = hotelRepository.findAll(PageRequest.of(page, batchSize));
            if(hotelPage.isEmpty()) {
                break;
            }
            hotelPage.getContent().forEach(this::updateHotelPricing);
            page++;
        }
    }

    private void updateHotelPricing(Hotel hotel){
        LocalDate startDate = LocalDate.now();
        LocalDate endDate = startDate.plusYears(1);

        List<Inventory> inventoryList = inventoryRepository.findByHotelAndDateBetween(hotel, startDate, endDate);
        updateInventoryPricing(inventoryList);
        updateHotelMinPricing(hotel, inventoryList, startDate, endDate);
    }

    private void updateHotelMinPricing(Hotel hotel, List<Inventory> inventoryList, LocalDate startDate, LocalDate endDate) {
        Map<LocalDate, BigDecimal> dailyMinPrices = inventoryList.stream()
                .collect(Collectors.groupingBy(
                        Inventory::getDate,
                        Collectors.mapping(Inventory::getPrice, Collectors.minBy(Comparator.naturalOrder()))
                ))
                .entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, e-> e.getValue().orElse(BigDecimal.ZERO)));

        List<HotelMinPrice> hotelPrices = new ArrayList<>();
        dailyMinPrices.forEach((date, price) -> {
            HotelMinPrice hotelMinPrice = hotelMinPriceRepository.findByHotelAndDate(hotel, date).orElse(new HotelMinPrice(hotel, date));
            hotelMinPrice.setPrice(price);
            hotelPrices.add(hotelMinPrice);
        });

        hotelMinPriceRepository.saveAll(hotelPrices);
    }

    private void updateInventoryPricing(List<Inventory> inventoryList){
        inventoryList.forEach(inventory -> {
            BigDecimal pricing = pricingService.calculateDynamicPricing(inventory);
            inventory.setPrice(pricing);
        });

        inventoryRepository.saveAll(inventoryList);
    }

}
