package com.project.airbnb.entity.inventory;

import com.project.airbnb.entity.room.Room;
import com.project.airbnb.entity.hotel.Hotel;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "inventory")
@Builder
@AllArgsConstructor
@NoArgsConstructor
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

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "total_count")
    private Integer totalCount;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "last_updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "surge_factor", precision = 5, scale = 2)
    private BigDecimal surgeFactor;

    @Column(name = "closed")
    private Boolean closed;

    @Column(name = "booked_count", nullable = false, columnDefinition = "INTEGER DEFAULT 0")
    private Integer bookedCount;

    @Column(name = "price", nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

    @Column(name = "city", nullable = false)
    private String city;
}
