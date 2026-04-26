package com.project.airbnb.dto.hotel;

import com.project.airbnb.entity.contactinfo.ContactInfo;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class HotelDto {

    private Long id;

    private String hotelName;

    private String city;

    private ContactInfo contactInfo;

    private List<String> photoes;

    private List<String> amenities;

    private Boolean isActive;
}
