package com.ozgurokanozdal.habitTracker.controller;

import com.ozgurokanozdal.habitTracker.dto.ActivityResponse;
import com.ozgurokanozdal.habitTracker.service.ActivityService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/activity")
public class ActivityController {


    private final ActivityService activityService;


    public ActivityController(ActivityService activityService){
        this.activityService = activityService;
    }


    @GetMapping
    public ResponseEntity<List<ActivityResponse>> getAll(){
        return ResponseEntity.ok(activityService.getAll());
    }









}
