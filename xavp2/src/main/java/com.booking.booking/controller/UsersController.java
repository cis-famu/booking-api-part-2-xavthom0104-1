package com.booking.booking.controller;
import com.booking.booking.model.Users;
import com.booking.booking.service.UsersService;
import com.booking.booking.util.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/users")

public class UsersController {
    private UsersService usersService;

    public UsersController(UsersService usersService){
        this.usersService = usersService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse> getAllUsers(){
        try{
            return ResponseEntity.ok(new ApiResponse(true, "Success", usersService.getAllUsers(), null));
        }
        catch(Exception e){
            return ResponseEntity.status(500).body(new ApiResponse(false,"An error occurred.", null, e.getMessage()));
        }
    }
    @GetMapping("/{usersId}")
    public ResponseEntity<ApiResponse> getUsersbyId(@PathVariable String usersId){
        try{
            return ResponseEntity.ok(new ApiResponse(true, "Success", usersService.getUsersById(usersId), null));
        }
        catch(Exception e){
            return ResponseEntity.status(500).body(new ApiResponse(false,"An error occurred.", null, e.getMessage()));
        }

    }
    @PostMapping
    public ResponseEntity<ApiResponse> createNewUsers(@RequestBody Users users){
        try{
            return ResponseEntity.ok(new ApiResponse(true,"Success", usersService.createUsers(users),null));
        } catch (ExecutionException e){
            return ResponseEntity.status(401).body(new ApiResponse(false, "An error occured", null, e.getMessage()));
        } catch (InterruptedException e){
            return ResponseEntity.status(500).body(new ApiResponse(false,"An error occurred", null, e.getMessage()));
        }
    }
    @PutMapping("/{user}")
    public ResponseEntity<ApiResponse> updateUsers(@PathVariable String user,@RequestBody Map<String, String> j ){
        try{
            usersService.updateUsers(user,j);
            return ResponseEntity.ok(new ApiResponse(true,"Update Success",null,null));
        } catch (Exception e){
            return ResponseEntity.status(500).body(new ApiResponse(false, "An error occurred", null, e.getMessage()));
        }
    }
    @DeleteMapping("/{userID}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable String userID){
        try{
            usersService.deleteUser(userID);
            return ResponseEntity.ok(new ApiResponse(true,"Update Success",null,null));
        } catch (Exception e){
            return ResponseEntity.status(500).body(new ApiResponse(false, "An error occurred", null, e.getMessage()));
        }

    }

}
