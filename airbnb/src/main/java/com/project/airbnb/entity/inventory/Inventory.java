package com.project.airbnb.entity.inventory;

import com.project.airbnb.entity.room.Room;
import com.project.airbnb.entity.hotel.Hotel;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "inventory")
public class Inventory {

    @Id
    @GeneratedValue(generator = "inventory_seq_gen", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "inventory_seq_gen", sequenceName = "inventory_seq", allocationSize = 1, initialValue = 1)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;

    @Column(name = "booking_date")
    private LocalDate bookingDate;

    @Column(name = "total_count")
    private Integer totalCount;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "last_updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "surge_factor")
    private Double surgeFactor;

    @Column(name = "closed")
    private Boolean closed;

    public Long getId() {
        return id;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public Room getRoom() {
        return room;
    }

    public LocalDate getBookingDate() {
        return bookingDate;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public Double getSurgeFactor() {
        return surgeFactor;
    }

    public Boolean getClosed() {
        return closed;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public void setBookingDate(LocalDate bookingDate) {
        this.bookingDate = bookingDate;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public void setSurgeFactor(Double surgeFactor) {
        this.surgeFactor = surgeFactor;
    }

    public void setClosed(Boolean closed) {
        this.closed = closed;
    }
}
