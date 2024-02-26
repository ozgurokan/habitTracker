package com.ozgurokanozdal.habitTracker.service;


import com.ozgurokanozdal.habitTracker.dto.ActivityCreateRequest;
import com.ozgurokanozdal.habitTracker.dto.ActivityResponse;
import com.ozgurokanozdal.habitTracker.entity.Activity;
import com.ozgurokanozdal.habitTracker.entity.Habit;
import com.ozgurokanozdal.habitTracker.exceptions.ContentNotFoundException;
import com.ozgurokanozdal.habitTracker.repository.ActivityRepository;
import com.ozgurokanozdal.habitTracker.repository.HabitRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ActivityService {

    private final ActivityRepository activityRepository;
    private final HabitRepository habitRepository;
    private final ModelMapper modelMapper;


    public ActivityService(ActivityRepository activityRepository,HabitRepository habitRepository, ModelMapper modelMapper){
        this.activityRepository = activityRepository;
        this.habitRepository = habitRepository;
        this.modelMapper = modelMapper;
    }

    public List<ActivityResponse> getAll(){
        return activityRepository.findAll().stream().map(element -> modelMapper.map(element,ActivityResponse.class)).collect(Collectors.toList());
    }


    public ActivityResponse get(Long activityId){
        Activity activity = activityRepository.findById(activityId).orElseThrow(ContentNotFoundException::new);
        return modelMapper.map(activity,ActivityResponse.class);
    }

    public ActivityResponse create(ActivityCreateRequest activityCreateRequest) {
        Habit habit = habitRepository.findById(activityCreateRequest.getHabit_id()).orElseThrow(ContentNotFoundException::new);
        Activity activity = new Activity(activityCreateRequest.getName(),habit, Instant.now());
        activityRepository.save(activity);
        return modelMapper.map(activity,ActivityResponse.class);
    }

    public ActivityResponse update(Long activityId, ActivityCreateRequest activityCreateRequest) {
        Activity activity = activityRepository.findById(activityId).orElseThrow(ContentNotFoundException::new);
        activity.setName(activityCreateRequest.getName());
        activityRepository.save(activity);
        return modelMapper.map(activity,ActivityResponse.class);
    }

    public String delete(Long activityId) {
        Activity activity = activityRepository.findById(activityId).orElseThrow(ContentNotFoundException::new);
        activityRepository.delete(activity);
        return "Activity -> " + activityId + " deleted";
    }
}
