package com.project.airbnb.entity.bookingguest;

import com.project.airbnb.entity.booking.Booking;
import com.project.airbnb.entity.guest.Guest;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookingGuest {

    @Id
    @GeneratedValue(generator = "booking_guest_seq_gen",strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "booking_guest_seq_gen", sequenceName = "booking_guest_seq", allocationSize = 1, initialValue = 1)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "booking_id")
    private Booking booking;

    @ManyToOne
    @JoinColumn(name = "guest_id")
    private Guest guest;
}
