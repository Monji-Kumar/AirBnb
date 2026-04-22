package com.project.airbnb.dto.hotel;

import com.project.airbnb.entity.hotel.Hotel;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

public class RoomDto {

    private Long id;

    private Hotel hotel;

    private String type;

    private Double basePrice;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private List<String> amenities;

    private List<String> photoes;

    private Integer totalCount;

    private Integer capacity;

    private Integer status;

    public Long getId() {
        return id;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public String getType() {
        return type;
    }

    public Double getBasePrice() {
        return basePrice;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public List<String> getAmenities() {
        return amenities;
    }

    public List<String> getPhotoes() {
        return photoes;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public Integer getStatus() {
        return status;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setBasePrice(Double basePrice) {
        this.basePrice = basePrice;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public void setAmenities(List<String> amenities) {
        this.amenities = amenities;
    }

    public void setPhotoes(List<String> photoes) {
        this.photoes = photoes;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
