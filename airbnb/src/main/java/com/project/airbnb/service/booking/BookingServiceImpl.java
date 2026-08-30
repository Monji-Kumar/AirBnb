package com.project.airbnb.service.booking;

import com.project.airbnb.dto.booking.BookingDto;
import com.project.airbnb.dto.booking.BookingRequestDto;
import com.project.airbnb.dto.guest.GuestDto;
import com.project.airbnb.entity.booking.Booking;
import com.project.airbnb.entity.booking.BookingRepository;
import com.project.airbnb.entity.guest.Guest;
import com.project.airbnb.entity.guest.GuestRepository;
import com.project.airbnb.entity.hotel.Hotel;
import com.project.airbnb.entity.inventory.Inventory;
import com.project.airbnb.entity.inventory.InventoryRepository;
import com.project.airbnb.entity.room.Room;
import com.project.airbnb.entity.user.User;
import com.project.airbnb.enums.BookingStatus;
import com.project.airbnb.exception.ResourceNotFoundException;
import com.project.airbnb.service.hotel.HotelService;
import com.project.airbnb.service.room.RoomService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;
    private final HotelService hotelService;
    private final RoomService roomService;
    private final InventoryRepository inventoryRepository;
    private final GuestRepository guestRepository;
    private final ModelMapper modelMapper;

    @Override
    @Transactional
    public BookingDto intializeBooking(BookingRequestDto bookingRequestDto) {
        log.info("Initializing booking for hotel : {}, room : {}, date: {} - {}", bookingRequestDto.getHotelId(), bookingRequestDto.getRoomId(), bookingRequestDto.getCheckInDate(), bookingRequestDto.getCheckOutDate());

        Hotel hotel = hotelService.findHotelById(bookingRequestDto.getHotelId());
        Room room = roomService.findByHotelIdAndRoomId(bookingRequestDto.getHotelId(),bookingRequestDto.getRoomId());
        List<Inventory> inventoryList = inventoryRepository.findAndLockAvailableInventory(bookingRequestDto.getRoomId(), bookingRequestDto.getCheckInDate(), bookingRequestDto.getCheckOutDate(), bookingRequestDto.getRoomCount());

        long daysCount = ChronoUnit.DAYS.between(bookingRequestDto.getCheckInDate(), bookingRequestDto.getCheckOutDate()) + 1 ;
        if(inventoryList.isEmpty()) {
            throw new ResourceNotFoundException("No Rooms available for the given dates");
        } else if (inventoryList.size() != daysCount) {
            throw new IllegalStateException("There are " + inventoryList.size() + " Rooms available for the given dates");
        }

        //TODO: Update booking count of inventories
        inventoryList.forEach(inventory -> {
            inventory.setReservedCount(bookingRequestDto.getRoomCount());
        });

        inventoryRepository.saveAll(inventoryList);

        //TODO: Calcuate pricing

        //Create a new Booking
        User user = new User();
        user.setId(1l);
        Booking booking = Booking.builder()
                .bookingStatus(BookingStatus.RESERVED)
                .hotel(hotel)
                .room(room)
                .checkInDate(bookingRequestDto.getCheckInDate())
                .checkOutDate(bookingRequestDto.getCheckOutDate())
                .user(user)
                .roomsCount(bookingRequestDto.getRoomCount())
                .amount(BigDecimal.valueOf(10000.20))
                    .build();

        booking = bookingRepository.save(booking);
        return modelMapper.map(booking, BookingDto.class);
    }

    @Override
    @Transactional
    public BookingDto addGuests(long bookingId, List<GuestDto> guestList) {

        log.info("Adding guests for booking : {}", bookingId);

        Booking booking = bookingRepository.findById(bookingId).orElseThrow(() -> new ResourceNotFoundException("Booking not found with id: " + bookingId));

        if(hasBookingExpired(booking)) {
            booking.setBookingStatus(BookingStatus.EXPIRED);
            bookingRepository.save(booking);
            throw new IllegalStateException("Booking has expired");
        }

        if(!booking.getBookingStatus().equals(BookingStatus.RESERVED)) {
            throw new IllegalStateException("Booking has not been reserved");
        }

        for(GuestDto guestDto : guestList) {
            Guest guest = modelMapper.map(guestDto, Guest.class);
            guest.setUser(getCurrentUser());
            guest = guestRepository.save(guest);
            booking.getGuests().add(guest);
        }
        booking.setBookingStatus(BookingStatus.GUESTS_ADDED);
        booking = bookingRepository.save(booking);
        return modelMapper.map(booking, BookingDto.class);
    }

    private boolean hasBookingExpired(Booking booking) {
        return booking.getCreatedAt().plusMinutes(10).isBefore(LocalDateTime.now());
    }

    private User getCurrentUser() {
        User user = new User();
        user.setId(1l);
        return user;
    }
}
