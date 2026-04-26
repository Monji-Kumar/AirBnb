package com.project.airbnb.controller;

import com.project.airbnb.dto.room.RoomDto;
import com.project.airbnb.service.room.RoomService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping(value = "/admin/hotels/{hotelId}/rooms")
@RequiredArgsConstructor
public class RoomAdminController {

    private final RoomService roomService;

    @PostMapping(value = "create-room")
    public ResponseEntity<?> createNewHotelRoom(@PathVariable(value = "hotelId") Long hotelId, @RequestBody RoomDto roomDto) {
        return new ResponseEntity<>(roomService.createNewRoom(hotelId, roomDto), HttpStatus.CREATED);
    }

    @GetMapping(value = "by-room")
    public ResponseEntity<?> getRoomByRoomId(@RequestParam(value = "id") Long id) {
        return new ResponseEntity<>(roomService.getRoomById(id), HttpStatus.FOUND);
    }

    @GetMapping(value = "by-hotel")
    public ResponseEntity<?> getRoomByHotelId(@PathVariable(value = "hotelId") Long id) {
        return new ResponseEntity<>(roomService.getAllRoomsInHotel(id), HttpStatus.FOUND);
    }

    @DeleteMapping(value = "delete-room")
    public ResponseEntity<?> deleteRoomById(@RequestParam(value = "id") Long id) {
        return new ResponseEntity<>(roomService.deleteRoomById(id), HttpStatus.NO_CONTENT);
    }

    @PatchMapping(value = "update-room-status")
    public ResponseEntity<?> updateStatusofRoomById(@RequestParam(value = "id") Long id, @RequestBody Map<String, String> requestBody) {
        return new ResponseEntity<>(roomService.updateStatusOfRoomById(id, requestBody.get("status")), HttpStatus.ACCEPTED);
    }


}
