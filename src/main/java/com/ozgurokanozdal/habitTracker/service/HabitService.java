package com.ozgurokanozdal.habitTracker.service;


import com.ozgurokanozdal.habitTracker.dto.*;
import com.ozgurokanozdal.habitTracker.entity.Habit;
import com.ozgurokanozdal.habitTracker.entity.User;
import com.ozgurokanozdal.habitTracker.exceptions.ContentNotFoundException;
import com.ozgurokanozdal.habitTracker.exceptions.UserNotFoundException;
import com.ozgurokanozdal.habitTracker.repository.HabitRepository;
import com.ozgurokanozdal.habitTracker.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class HabitService {


    private final HabitRepository habitRepository;
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;

    public HabitService(HabitRepository habitRepository, ModelMapper modelMapper,UserRepository userRepository){
        this.habitRepository = habitRepository;
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
    }


    public List<HabitResponse> getAll(){
        return habitRepository.findAll().stream().map(element -> modelMapper.map(element, HabitResponse.class)).collect(Collectors.toList());
    }

    public HabitResponse getById(Long habitId){
        Habit habit  = habitRepository.findById(habitId).orElseThrow(ContentNotFoundException::new);
        return modelMapper.map(habit, HabitResponse.class);
    }

    public HabitCreateRequest create(HabitCreateRequest habitCreateRequest){
        User user = userRepository.findById(habitCreateRequest.getUserId()).orElseThrow(UserNotFoundException::new);
        Habit habit = new Habit(habitCreateRequest.getName(),user);
        habitRepository.save(habit);
        return modelMapper.map(habit, HabitCreateRequest.class);
    }

    public HabitResponse update(Long habitId,HabitUpdateRequest habitUpdateRequest){
        Habit habit = habitRepository.findById(habitId).orElseThrow(ContentNotFoundException::new);
        habit.setName(habitUpdateRequest.getName());
        habitRepository.save(habit);
        return modelMapper.map(habit, HabitResponse.class);
    }


    public String delete(Long habitId) {
        Habit habit = habitRepository.findById(habitId).orElseThrow(ContentNotFoundException::new);
        habitRepository.delete(habit);
        return "Habit -> " + habitId + " deleted";

    }

    public List<ActivityResponse> getHabitActivityList(Long habitId){
        Habit habit = habitRepository.findById(habitId).orElseThrow(ContentNotFoundException::new);
        return habit.getActivities().stream().map(activity -> modelMapper.map(activity,ActivityResponse.class)).toList();
    }
}
