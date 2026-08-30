package com.project.airbnb.entity.booking;

import com.project.airbnb.entity.guest.Guest;
import com.project.airbnb.entity.hotel.Hotel;
import com.project.airbnb.entity.payment.Payment;
import com.project.airbnb.entity.room.Room;
import com.project.airbnb.entity.user.User;
import com.project.airbnb.enums.BookingStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "booking")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Booking {

    @Id
    @GeneratedValue(generator = "booking_seq_gen", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(initialValue = 1, sequenceName = "booking_seq", allocationSize = 1, name = "booking_seq_gen")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "last_updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "status")
    private BookingStatus status;

    @Column(nullable = false)
    private Integer roomsCount;

    @Column(name = "check_in_date")
    private LocalDate checkInDate;

    @Column(name = "check_out_date")
    private LocalDate checkOutDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "booking_status")
    private BookingStatus bookingStatus;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "booking_guests",
            joinColumns = @JoinColumn(name = "booking_id"),
            inverseJoinColumns = @JoinColumn(name = "guest_id")
    )
    private Set<Guest> guests;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal amount;
}
