package com.project.airbnb.strategy;

import com.project.airbnb.entity.inventory.Inventory;

import java.math.BigDecimal;

public interface PricingStrategy {
    BigDecimal calculatePrice(Inventory inventory);

}
