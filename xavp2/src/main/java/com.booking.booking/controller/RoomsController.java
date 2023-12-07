package com.booking.booking.controller;
import com.booking.booking.model.Rooms;
import com.booking.booking.model.Rooms;
import com.booking.booking.service.RoomsService;
import com.booking.booking.util.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/rooms")

public class RoomsController {
    private RoomsService roomsService;

    public RoomsController(RoomsService roomsService) {
        this.roomsService = roomsService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse> getAllRooms() {
        try {
            return ResponseEntity.ok(new ApiResponse(true, "Success", roomsService.getAllRooms(), null));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new ApiResponse(false, "An error occurred.", null, e.getMessage()));
        }
    }

    @GetMapping("/{roomsId}")
    public ResponseEntity<ApiResponse> getRoomsbyId(@PathVariable String roomsId) {
        try {
            return ResponseEntity.ok(new ApiResponse(true, "Success", roomsService.getRoomsById(roomsId), null));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new ApiResponse(false, "An error occurred.", null, e.getMessage()));
        }

    }
    @PostMapping
    public ResponseEntity<ApiResponse> createNewRooms(@RequestBody Rooms rooms){
        try{
            return ResponseEntity.ok(new ApiResponse(true,"Success", roomsService.createRooms(rooms),null));
        } catch (ExecutionException e){
            return ResponseEntity.status(401).body(new ApiResponse(false, "An error occured", null, e.getMessage()));
        } catch (InterruptedException e){
            return ResponseEntity.status(500).body(new ApiResponse(false,"An error occurred", null, e.getMessage()));
        }
    }
    @PutMapping("/{rooms}")
    public ResponseEntity<ApiResponse> updateRooms(@PathVariable String rooms,@RequestBody Map<String, String> j ){
        try{
            roomsService.updateRooms(rooms,j);
            return ResponseEntity.ok(new ApiResponse(true,"Update Success",null,null));
        } catch (Exception e){
            return ResponseEntity.status(500).body(new ApiResponse(false, "An error occurred", null, e.getMessage()));
        }
    }
    @DeleteMapping("/{roomID}")
    public ResponseEntity<ApiResponse> deleteRoom(@PathVariable String roomID){
        try{
            roomsService.deleteRoom(roomID);
            return ResponseEntity.ok(new ApiResponse(true,"Update Success",null,null));
        } catch (Exception e){
            return ResponseEntity.status(500).body(new ApiResponse(false, "An error occurred", null, e.getMessage()));
        }

    }
}