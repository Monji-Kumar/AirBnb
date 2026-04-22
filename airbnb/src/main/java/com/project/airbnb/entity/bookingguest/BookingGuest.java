package com.project.airbnb.entity.bookingguest;

import com.project.airbnb.entity.booking.Booking;
import com.project.airbnb.entity.guest.Guest;
import jakarta.persistence.*;

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

    public Long getId() {
        return id;
    }

    public Booking getBooking() {
        return booking;
    }

    public Guest getGuest() {
        return guest;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }

    public void setGuest(Guest guest) {
        this.guest = guest;
    }
}
