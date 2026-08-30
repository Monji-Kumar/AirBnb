package com.project.airbnb.strategy;

import com.project.airbnb.entity.inventory.Inventory;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@AllArgsConstructor
public class HolidayPricingStrategy implements PricingStrategy{

    private PricingStrategy wrapped;

    @Override
    public BigDecimal calculatePrice(Inventory inventory) {
        BigDecimal price = wrapped.calculatePrice(inventory);
        boolean istodayHoliday = true;       //TODO: verify is today a holiday or not
        if(istodayHoliday){
            price = price.multiply(BigDecimal.valueOf(0.25));
        }
        return price;
    }
}
