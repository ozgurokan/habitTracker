package com.ozgurokanozdal.habitTracker.controller;


import com.ozgurokanozdal.habitTracker.dto.HabitResponse;
import com.ozgurokanozdal.habitTracker.dto.UserCreateRequest;
import com.ozgurokanozdal.habitTracker.dto.UserResponse;
import com.ozgurokanozdal.habitTracker.dto.UserUpdateRequest;
import com.ozgurokanozdal.habitTracker.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@ResponseBody
@RequestMapping("/user")
public class UserController {


    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }



    @GetMapping
    public ResponseEntity<List<UserResponse>> getAll(){
        return ResponseEntity.ok(userService.getAll());
    }

    @PostMapping
    public ResponseEntity<UserResponse> create(@RequestBody UserCreateRequest userCreateRequest){
        return ResponseEntity.ok(userService.save(userCreateRequest));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserResponse> get(@PathVariable Long userId){
        return ResponseEntity.ok(userService.getOneById(userId));
    }

    @GetMapping("/{userId}/habits")
    public ResponseEntity<List<HabitResponse>> getUserHabits(@PathVariable Long userId){
        return ResponseEntity.ok(userService.getUserHabitList(userId));
    }


    @PutMapping("/{userId}")
    public ResponseEntity<UserUpdateRequest> update(@PathVariable Long userId, @RequestBody UserUpdateRequest userUpdateRequest){
        return ResponseEntity.ok(userService.update(userId,userUpdateRequest));
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<String> delete(@PathVariable Long userId){
        return ResponseEntity.ok(userService.delete(userId));
    }





}
