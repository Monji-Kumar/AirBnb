package com.project.airbnb.entity.room;

import com.project.airbnb.entity.hotel.Hotel;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "room")
public class Room {

    @Id
    @GeneratedValue(generator = "room_seq_gen", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(initialValue = 1, allocationSize = 1, sequenceName = "room_seq", name = "room_seq_gen")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;

    @Column(name = "type")
    private String type;

    @Column(name = "base_price", columnDefinition = "DOUBLE PRECISION")
    private Double basePrice;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "last_updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "amenities")
    private List<String> amenities;

    @Column(name = "photoes", columnDefinition = "TEXT[]")
    private List<String> photoes;

    @Column(name = "total_count")
    private Integer totalCount;

    @Column(name = "capacity")
    private Integer capacity;

    @Column(name = "status")
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
