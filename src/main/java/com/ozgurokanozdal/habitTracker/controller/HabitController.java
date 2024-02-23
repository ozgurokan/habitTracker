package com.ozgurokanozdal.habitTracker.controller;

import com.ozgurokanozdal.habitTracker.dto.ActivityResponse;
import com.ozgurokanozdal.habitTracker.dto.HabitCreateRequest;
import com.ozgurokanozdal.habitTracker.dto.HabitResponse;
import com.ozgurokanozdal.habitTracker.dto.HabitUpdateRequest;
import com.ozgurokanozdal.habitTracker.service.HabitService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@ResponseBody
@RequestMapping("/habit")
public class HabitController {

    private final HabitService habitService;

    public HabitController(HabitService habitService){
        this.habitService = habitService;
    }


    @GetMapping
    public ResponseEntity<List<HabitResponse>> getAll(){
        return ResponseEntity.ok(habitService.getAll());
    }

    @PostMapping
    public ResponseEntity<HabitCreateRequest> create(@RequestBody HabitCreateRequest habitCreateRequest){
        return ResponseEntity.ok(habitService.create(habitCreateRequest));
    }

    @GetMapping("/{habitId}")
    public ResponseEntity<HabitResponse> get(@PathVariable Long habitId){
        return ResponseEntity.ok(habitService.getById(habitId));
    }

    @PutMapping("/{habitId}")
    public ResponseEntity<HabitResponse> update(@PathVariable Long habitId, @RequestBody HabitUpdateRequest habitUpdateRequest){
        return ResponseEntity.ok(habitService.update(habitId,habitUpdateRequest));
    }

    @DeleteMapping("/{habitId}")
    public ResponseEntity<String> delete(@PathVariable Long habitId){
        return ResponseEntity.ok(habitService.delete(habitId));
    }

    @GetMapping("/{habitId}/activities")
    public ResponseEntity<List<ActivityResponse>> getHabitActivities(@PathVariable Long habitId){
        return ResponseEntity.ok(habitService.getHabitActivityList(habitId));
    }


}
