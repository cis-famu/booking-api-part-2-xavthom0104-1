package com.booking.booking.controller;
import com.booking.booking.model.Hotels;
import com.booking.booking.service.HotelsService;
import com.booking.booking.util.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/hotels")

public class HotelsController {
    private HotelsService hotelsService;

    public HotelsController(HotelsService hotelsService){
        this.hotelsService = hotelsService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse> getAllHotels(){
        try{
            return ResponseEntity.ok(new ApiResponse(true, "Success", hotelsService.getAllHotels(), null));
        }
        catch(Exception e){
            return ResponseEntity.status(500).body(new ApiResponse(false,"An error occurred.", null, e.getMessage()));
        }
    }
    @GetMapping("/{hotelsId}")
    public ResponseEntity<ApiResponse> getHotelsbyId(@PathVariable String hotelsId){
        try{
            return ResponseEntity.ok(new ApiResponse(true, "Success", hotelsService.getHotelsById(hotelsId), null));
        }
        catch(Exception e){
            return ResponseEntity.status(500).body(new ApiResponse(false,"An error occurred.", null, e.getMessage()));
        }

    }
    @PostMapping
    public ResponseEntity<ApiResponse> createNewHotels(@RequestBody Hotels hotels){
        try{
            return ResponseEntity.ok(new ApiResponse(true,"Success", hotelsService.createHotels(hotels),null));
        } catch (ExecutionException e){
            return ResponseEntity.status(401).body(new ApiResponse(false, "An error occured", null, e.getMessage()));
        } catch (InterruptedException e){
            return ResponseEntity.status(500).body(new ApiResponse(false,"An error occurred", null, e.getMessage()));
        }
    }
    @PutMapping("/{hotels}")
    public ResponseEntity<ApiResponse> updateHotels(@PathVariable String hotels,@RequestBody Map<String, String> j ){
        try{
            hotelsService.updateHotels(hotels,j);
            return ResponseEntity.ok(new ApiResponse(true,"Update Success",null,null));
        } catch (Exception e){
            return ResponseEntity.status(500).body(new ApiResponse(false, "An error occurred", null, e.getMessage()));
        }
    }
    @DeleteMapping("/{hotelID}")
    public ResponseEntity<ApiResponse> deleteHotel(@PathVariable String hotelID){
        try{
            hotelsService.deleteHotel(hotelID);
            return ResponseEntity.ok(new ApiResponse(true,"Update Success",null,null));
        } catch (Exception e){
            return ResponseEntity.status(500).body(new ApiResponse(false, "An error occurred", null, e.getMessage()));
        }

    }


}
