package com.mahipaul.notes_application.controller;

import com.mahipaul.notes_application.dto.LoginBody;
import com.mahipaul.notes_application.dto.UserDto;
import com.mahipaul.notes_application.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserService userService;

    //create
    @PostMapping("/create-user")
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto user){
        UserDto userDto =  this.userService.createUser(user);
        return ResponseEntity.ok(userDto);
    }

    //update
    @PutMapping("/{userId}")
    public ResponseEntity<UserDto> updateUser(@RequestBody UserDto user,@PathVariable UUID userId){
        UserDto userDto =  this.userService.updateUser(user, userId);
        return ResponseEntity.ok(userDto);
    }
    //delete
    @DeleteMapping("/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable UUID userId){
        this.userService.deleteUser(userId);
        return ResponseEntity.ok("User deleted successfully");
    }

    //get
    @GetMapping("/get-user")
    public ResponseEntity<UserDto> getUser(@RequestParam("id") UUID userId){
        UserDto userDto =  this.userService.getUser(userId);
        return ResponseEntity.ok(userDto);
    }

    //get all
    @GetMapping("/")
    public ResponseEntity<List<UserDto>> getAllUsers(){
        List<UserDto> userDtos =  this.userService.getAllUser();
        return ResponseEntity.ok(userDtos);
    }

    @PostMapping("/login")
    public ResponseEntity<UserDto> userLogin(@RequestBody LoginBody loginBody){
        UserDto apiRes = new UserDto();
        try {
            apiRes =  this.userService.userLogin(loginBody.getEmail(), loginBody.getPassword());
            return ResponseEntity.ok(apiRes);
        } catch (Exception e) {
            return new ResponseEntity<UserDto>(apiRes, HttpStatus.UNAUTHORIZED);
        }
    }

}
