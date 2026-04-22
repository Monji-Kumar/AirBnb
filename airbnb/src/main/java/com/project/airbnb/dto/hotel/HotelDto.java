package com.project.airbnb.dto.hotel;

import com.project.airbnb.entity.contactinfo.ContactInfo;

import java.time.LocalDateTime;
import java.util.List;

public class HotelDto {

    private Long id;

    private String hotelName;

    private String city;

    private ContactInfo contactInfo;

    private List<String> photoes;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private List<String> amenities;

    private Boolean isActive;

    public Long getId() {
        return id;
    }

    public String getCity() {
        return city;
    }

    public ContactInfo getContactInfo() {
        return contactInfo;
    }

    public List<String> getPhotoes() {
        return photoes;
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

    public Boolean getActive() {
        return isActive;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setContactInfo(ContactInfo contactInfo) {
        this.contactInfo = contactInfo;
    }

    public void setPhotoes(List<String> photoes) {
        this.photoes = photoes;
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

    public void setActive(Boolean active) {
        isActive = active;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }
}
