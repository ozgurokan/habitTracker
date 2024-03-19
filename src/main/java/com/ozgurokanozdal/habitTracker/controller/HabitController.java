package com.ozgurokanozdal.habitTracker.controller;

import com.ozgurokanozdal.habitTracker.dto.*;
import com.ozgurokanozdal.habitTracker.entity.Habit;
import com.ozgurokanozdal.habitTracker.entity.Likes;
import com.ozgurokanozdal.habitTracker.service.HabitService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/habit")
public class HabitController {

    private final HabitService habitService;

    public HabitController(HabitService habitService){
        this.habitService = habitService;
    }


    @GetMapping
    public ResponseEntity<List<HabitResponse>> getAll(){
        return ResponseEntity.ok(habitService.getAll());
    }

    @GetMapping("/paginate")
    public ResponseEntity<Page<HabitResponse>> getAllWithPage(Pageable pageable){
        return ResponseEntity.ok(habitService.getAllWithPage(pageable));
    }

    @PostMapping
    public ResponseEntity<HabitResponse> create(@RequestBody HabitCreateRequest habitCreateRequest){
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



    // this end point returns activity list of a habit with pagination.
    @GetMapping("/{habitId}/activities/paginate")
    public ResponseEntity<Page<ActivityResponse>> getHabitActivitiesWithPage(@PathVariable long habitId,Pageable pageable){
        return ResponseEntity.ok(habitService.getHabitActivityListWithPage(habitId,pageable));
    }

    @GetMapping("/{habitId}/likes/paginate")
    public ResponseEntity<Page<LikeResponse>> getHabitLikesWithPage(@PathVariable long habitId, Pageable pageable){
        return ResponseEntity.ok(habitService.getHabitLikesListWithPage(habitId,pageable));
    }


}
