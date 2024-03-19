package com.ozgurokanozdal.habitTracker.controller;


import com.ozgurokanozdal.habitTracker.dto.HabitResponse;
import com.ozgurokanozdal.habitTracker.dto.UserResponse;
import com.ozgurokanozdal.habitTracker.dto.UserUpdateRequest;
import com.ozgurokanozdal.habitTracker.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/user")
public class UserController {


    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }



    @GetMapping
    public ResponseEntity<List<UserResponse>> getAll(){
        return ResponseEntity.ok(userService.getAll());
    }

    @GetMapping("/paginate")
    public ResponseEntity<Page<UserResponse>> getAllWithPage(Pageable pageable){
        return ResponseEntity.ok(userService.getAllWithPage(pageable));
    }

    @GetMapping("/{username}")
    public ResponseEntity<UserResponse> get(@PathVariable String username){
        return ResponseEntity.ok(userService.getOneByUsername(username));
    }

    @GetMapping("/{username}/habits")
    public ResponseEntity<List<HabitResponse>> getUserHabits(@PathVariable String username){
        return ResponseEntity.ok(userService.getUserHabitList(username));
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
