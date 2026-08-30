package com.project.airbnb.strategy;

import com.project.airbnb.entity.inventory.Inventory;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
@AllArgsConstructor
public class UrgencyPricingStrategy implements PricingStrategy{

    private final PricingStrategy wrapped;

    @Override
    public BigDecimal calculatePrice(Inventory inventory) {
        BigDecimal price = wrapped.calculatePrice(inventory);
        LocalDate now = LocalDate.now();
        if(!inventory.getDate().isBefore(now) && now.isBefore(inventory.getDate().plusDays(7))){
            return price.multiply(BigDecimal.valueOf(1.15));
        }
        return price;
    }
}
