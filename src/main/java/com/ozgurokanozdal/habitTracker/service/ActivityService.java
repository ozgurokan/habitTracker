package com.ozgurokanozdal.habitTracker.service;


import com.ozgurokanozdal.habitTracker.dto.ActivityResponse;
import com.ozgurokanozdal.habitTracker.entity.Activity;
import com.ozgurokanozdal.habitTracker.repository.ActivityRepository;
import com.ozgurokanozdal.habitTracker.repository.HabitRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

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


}
