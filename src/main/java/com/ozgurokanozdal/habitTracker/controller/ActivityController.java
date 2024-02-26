package com.ozgurokanozdal.habitTracker.controller;

import com.ozgurokanozdal.habitTracker.dto.ActivityCreateRequest;
import com.ozgurokanozdal.habitTracker.dto.ActivityResponse;
import com.ozgurokanozdal.habitTracker.repository.HabitRepository;
import com.ozgurokanozdal.habitTracker.service.ActivityService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/activity")
public class ActivityController {


    private final ActivityService activityService;


    public ActivityController(ActivityService activityService){
        this.activityService = activityService;
    }


    @GetMapping
    public ResponseEntity<List<ActivityResponse>> getAll(){
        return ResponseEntity.ok(activityService.getAll());
    }

    @PostMapping
    public ResponseEntity<ActivityResponse> create(@RequestBody ActivityCreateRequest activityCreateRequest){
        return ResponseEntity.ok(activityService.create(activityCreateRequest));
    }

    @GetMapping("/{activityId}")
    public ResponseEntity<ActivityResponse> get(@PathVariable Long activityId){
        return ResponseEntity.ok(activityService.get(activityId));
    }

    @PutMapping("/{activityId}")
    public ResponseEntity<ActivityResponse> update(@PathVariable Long activityId,@RequestBody ActivityCreateRequest activityCreateRequest){
        return ResponseEntity.ok(activityService.update(activityId,activityCreateRequest));
    }

    @DeleteMapping("/{activityId}")
    public ResponseEntity<String> delete(@PathVariable Long activityId){
        return ResponseEntity.ok(activityService.delete(activityId));
    }










}
