package com.project.airbnb.entity.room;

import com.project.airbnb.entity.hotel.Hotel;
import com.project.airbnb.enums.BookingStatus;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "room")
@AllArgsConstructor
@NoArgsConstructor
@Builder
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
    private BigDecimal basePrice;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @UpdateTimestamp
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
    private BookingStatus status;
}
